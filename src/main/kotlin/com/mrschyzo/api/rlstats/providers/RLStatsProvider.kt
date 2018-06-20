package com.mrschyzo.api.rlstats.providers

import com.mrschyzo.api.rlstats.models.*

interface RLStatsProvider {
    fun getPlayer(playerId: String, platform: Platform): Player

    fun getPlayers(playersEntries: Collection<PlayerEntry>): Collection<Player>

    fun getLeaderboardBy(playlist: Playlist): Collection<Player>

    fun getLeaderboardBy(stat: Stat): Collection<Player>

    fun findPlayers(displayName: String): Collection<Player>

    fun findPlayers(displayName: String, resultsLimit: Int): Collection<Player>

    fun findPlayers(displayName: String, criteria: (Player) -> Boolean): Collection<Player>
}