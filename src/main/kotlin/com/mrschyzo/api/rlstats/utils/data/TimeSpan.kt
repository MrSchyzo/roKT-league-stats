package com.mrschyzo.api.rlstats.utils.data

import java.util.concurrent.TimeUnit

data class TimeSpan(
        val span: Long,
        val unit: TimeUnit
)