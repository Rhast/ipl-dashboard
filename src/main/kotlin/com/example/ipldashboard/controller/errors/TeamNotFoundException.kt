package com.example.ipldashboard.controller.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "team not found")
class TeamNotFoundException : RuntimeException() {
}
