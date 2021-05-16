package com.example.ipldashboard.data

import com.example.ipldashboard.model.Team
import org.slf4j.LoggerFactory
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.listener.JobExecutionListenerSupport
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Component
class JobCompletionNotificationListener @Autowired constructor(private val entityManager: EntityManager) :
    JobExecutionListenerSupport() {

    @Transactional
    override fun afterJob(jobExecution: JobExecution) {
        if (jobExecution.status == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results")
            val teamNameToMatchCount: Map<String, Long> =
                queryTeamToMatchCount("team1")
                    .union(queryTeamToMatchCount("team2"))
                    .associateBy({ it[0] as String }, { it[1] as Long })

            val teamNameToWinsCount: Map<String, Long> = entityManager
                .createQuery("SELECT matchWinner, count(*) FROM Match GROUP BY matchWinner", Array<Any>::class.java)
                .resultList
                .associateBy({ it[0] as String }, { it[1] as Long })

            teamNameToMatchCount
                .map {
                    val teamName = it.key
                    Team(teamName = teamName, totalMatches = it.value, totalWins = teamNameToWinsCount[teamName] ?: 0)
                }
                .forEach {
                    println(it)
                    entityManager.persist(it)
                }


        }
    }

    private fun queryTeamToMatchCount(teamField: String) = entityManager
        .createQuery("SELECT $teamField, count(*) FROM Match GROUP BY $teamField", Array<Any>::class.java)
        .resultList

    companion object {
        private val log = LoggerFactory.getLogger(JobCompletionNotificationListener::class.java)
    }
}
