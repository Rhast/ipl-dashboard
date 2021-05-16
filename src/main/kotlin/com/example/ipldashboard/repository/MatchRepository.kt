package com.example.ipldashboard.repository

import com.example.ipldashboard.model.Match
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface MatchRepository : CrudRepository<Match, Long> {

    fun findByTeam1OrTeam2OrderByDateDesc(
        teamName1: String,
        teamName2: String = teamName1,
        page: Pageable = Pageable.unpaged()
    ): List<Match>
}
