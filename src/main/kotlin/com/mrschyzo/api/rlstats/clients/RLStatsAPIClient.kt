package com.mrschyzo.api.rlstats.clients

import com.mrschyzo.api.rlstats.providers.RLDataProvider
import com.mrschyzo.api.rlstats.providers.RLStatsProvider

class RLStatsAPIClient (
        private val statsClient: RLStatsProvider,
        private val dataContainer: RLDataProvider
) : RLStatsProvider by statsClient, RLDataProvider by dataContainer
