package com.example.ipldashboard.controller

import com.example.ipldashboard.MatchDao
import com.example.ipldashboard.controller.dto.TeamDto
import com.example.ipldashboard.controller.errors.TeamNotFoundException
import com.example.ipldashboard.repository.TeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("*")
class TeamController @Autowired constructor(
    val teamRepository: TeamRepository,
    val matchDao: MatchDao
) {

    @GetMapping("/team/{teamName}")
    fun getTeam(@PathVariable teamName: String): TeamDto? {
        val team = teamRepository.findByTeamName(teamName) ?: throw TeamNotFoundException()

        return TeamDto(team, matchDao.findLatestMatches(teamName, 4))
    }
}
