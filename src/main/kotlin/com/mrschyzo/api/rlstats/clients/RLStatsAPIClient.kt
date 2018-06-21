package com.mrschyzo.api.rlstats.clients

import com.mrschyzo.api.rlstats.providers.*
import com.mrschyzo.api.rlstats.utils.data.*

class RLStatsAPIClient (
        private val statsClient: RLStatsProvider,
        private val dataContainer: RLDataProvider
) : RLStatsProvider by statsClient, RLDataProvider by dataContainer
{
    companion object {
        fun createFromProps(props: RetrofitServiceProperties) : RLStatsAPIClient {
            val service = props.generateRLService()

            return RLStatsAPIClient(
                statsClient = ApiRLStatsProvider(service),
                dataContainer = IndexedRLDataProvider(service)
            )
        }
    }
}
