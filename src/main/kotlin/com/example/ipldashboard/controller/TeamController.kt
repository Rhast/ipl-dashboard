package com.example.ipldashboard.controller

import com.example.ipldashboard.repository.MatchDao
import com.example.ipldashboard.controller.dto.TeamDto
import com.example.ipldashboard.controller.errors.TeamNotFoundException
import com.example.ipldashboard.model.Match
import com.example.ipldashboard.repository.TeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
class TeamController @Autowired constructor(
    val teamRepository: TeamRepository,
    val matchDao: MatchDao
) {

    @GetMapping("/team/{teamName}")
    fun getTeam(@PathVariable teamName: String): TeamDto? {
        val team = teamRepository.findByTeamName(teamName) ?: throw TeamNotFoundException()

        return TeamDto(team, matchDao.findLatestMatches(teamName, limit = 4))
    }

    @GetMapping("/team/{teamName}/matches")
    fun getTeamMatches(
        @PathVariable teamName: String,
        @RequestParam year: Int
    ): List<Match> {
        return matchDao.findLatestMatches(teamName, year)
    }
}
