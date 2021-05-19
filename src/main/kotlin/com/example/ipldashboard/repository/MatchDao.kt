package com.example.ipldashboard.repository

import com.example.ipldashboard.model.Match
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MatchDao @Autowired constructor(val matchRepository: MatchRepository) {
    fun findLatestMatches(teamName: String, year: Int? = null, limit: Int? = null): List<Match> {
        val page = pageable(limit)
        return if (year == null) {
            matchRepository.findByTeam1OrTeam2OrderByDateDesc(teamName, page = page)
        } else {
            matchRepository.findByTeamBetweenDates(
                teamName,
                LocalDate.of(year, 1, 1),
                LocalDate.of(year + 1, 1, 1),
                page
            )
        }
    }

    private fun pageable(limit: Int?): Pageable {
        return if (limit == null) {
            Pageable.unpaged()
        } else {
            PageRequest.of(0, limit)
        }
    }
}
