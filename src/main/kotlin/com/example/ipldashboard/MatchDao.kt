package com.example.ipldashboard

import com.example.ipldashboard.model.Match
import com.example.ipldashboard.repository.MatchRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class MatchDao @Autowired constructor(val matchRepository: MatchRepository) {
    fun findLatestMatches(teamName1: String, limit: Int): List<Match> =
        matchRepository.findByTeam1OrTeam2OrderByDateDesc(teamName1, page = PageRequest.of(0, limit))
}
