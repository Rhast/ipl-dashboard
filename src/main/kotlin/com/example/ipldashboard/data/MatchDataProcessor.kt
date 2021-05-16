package com.example.ipldashboard.data

import com.example.ipldashboard.model.Match
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemProcessor
import java.time.LocalDate

class MatchDataProcessor: ItemProcessor<MatchInput, Match> {
    companion object {
        private val logger = LoggerFactory.getLogger(MatchDataProcessor::class.java)
    }

    override fun process(matchInput: MatchInput): Match {
        return matchInput.toMatch()
    }

    private fun MatchInput.toMatch(): Match {
        val (team1, team2) = orderTeams()
        return Match(
            id!!.toLong(),
            city!!,
            LocalDate.parse(date)!!,
            player_of_match!!,
            venue!!,
            team1!!,
            team2!!,
            toss_winner!!,
            toss_decision!!,
            winner!!,
            result!!,
            result_margin!!,
            umpire1!!,
            umpire2!!,
        )
    }

    private fun MatchInput.orderTeams(): Pair<String?, String?> {
        val tossLooser = if (toss_winner == team1) team2 else team1

        return if (toss_decision == "bat")
            Pair(toss_winner, tossLooser)
        else
            Pair(tossLooser, toss_winner)
    }

}
