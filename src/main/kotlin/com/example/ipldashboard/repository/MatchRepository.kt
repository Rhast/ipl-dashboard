package com.example.ipldashboard.repository

import com.example.ipldashboard.model.Match
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.time.LocalDate

interface MatchRepository : CrudRepository<Match, Long> {

    fun findByTeam1OrTeam2OrderByDateDesc(
        teamName1: String,
        teamName2: String = teamName1,
        page: Pageable = Pageable.unpaged()
    ): List<Match>

    @Query("""
        SELECT m from Match m 
        where (m.team1 = :teamName or m.team2 = :teamName) 
        and m.date between :startDate and :endDate
        order by m.date desc
    """)
    fun findByTeamBetweenDates(
        @Param("teamName") teamName: String,
        @Param("startDate") startDate: LocalDate,
        @Param("endDate") endDate: LocalDate,
        page: Pageable = Pageable.unpaged()
    ): List<Match>

    fun findByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
        teamName1: String,
        date1: LocalDate,
        date2: LocalDate,
        teamName2: String = teamName1,
        date11: LocalDate = date1,
        date22: LocalDate = date2,
        page: Pageable = Pageable.unpaged()
    ): List<Match>
}
