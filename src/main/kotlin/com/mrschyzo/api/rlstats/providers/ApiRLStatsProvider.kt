package com.mrschyzo.api.rlstats.providers

import com.mrschyzo.api.rlstats.models.*
import com.mrschyzo.api.rlstats.services.RLService

class ApiRLStatsProvider (
        private val service: RLService
) : RLStatsProvider {
    override fun getPlayers(playersEntries: Collection<PlayerEntry>): Collection<Player> =
            service.getPlayers(playersEntries).getResponseOrException("Getting players")

    override fun getLeaderboardBy(playlist: Playlist): Collection<Player> {
        if (!playlist.isRanked())
            throw IllegalArgumentException("Given playlist is not a ranked one: $playlist")

        return service.getLeaderboardByRankedPlaylistId(playlist.id).getResponseOrException("Getting leaderboard for ${playlist.name}")
    }

    override fun getLeaderboardBy(stat: Stat): Collection<Player> =
            service.getLeaderboardByStatType(stat).getResponseOrException("Getting leaderboard for $stat stat")

    override fun findPlayers(displayName: String, criteria: (Player) -> Boolean): Collection<Player> =
            findPlayers(displayName).filter(criteria)

    override fun findPlayers(displayName: String): Collection<Player> =
            findPlayers(displayName, -1)

    override fun findPlayers(displayName: String, resultsLimit: Int): Collection<Player> {
        if (displayName.trim().length < 3)
            throw IllegalArgumentException("Player partial name must be at least 3 characters long")

        val result = service.searchPlayersByDisplayName(displayName).getResponseOrException("Getting total pages for player \"$displayName\"")
        val desiredResults = limitResult(resultsLimit, result.totalResults)
        val page = result.page + 1
        val maxPage = desiredResults / result.maxResultsPerPage

        val otherData = (page..maxPage).flatMap { service.searchPlayersByDisplayName(displayName, it).getResponseOrException("Getting page $it for player \"$displayName\"").data }

        return result.data.plus(otherData)
    }

    override fun getPlayer(playerId: String, platform: Platform): Player? {
        try {
            return service.getPlayer(playerId, platform.id).getResponseOrException("Getting player $playerId for platform ${platform.name}")
        } catch (ex: ResponseFailedException) {
            if (ex.rawResponse.code() != 404)
                throw ex
        }
        return null
    }

    private fun limitResult(resultsLimit: Int, actualResults: Int) =
            if (resultsLimit !in 0..actualResults) actualResults else resultsLimit
}