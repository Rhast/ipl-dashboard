package com.example.ipldashboard.repository

import com.example.ipldashboard.model.Team
import org.springframework.data.repository.CrudRepository

interface TeamRepository : CrudRepository<Team, Long> {
    fun findByTeamName(teamName: String): Team?
}
