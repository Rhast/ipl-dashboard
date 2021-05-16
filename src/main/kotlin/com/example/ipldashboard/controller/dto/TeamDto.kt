package com.example.ipldashboard.controller.dto

import com.example.ipldashboard.model.Match
import com.example.ipldashboard.model.Team
import com.fasterxml.jackson.annotation.JsonUnwrapped

data class TeamDto(
    @JsonUnwrapped
    val team: Team,
    val matches: List<Match> = listOf()
)

