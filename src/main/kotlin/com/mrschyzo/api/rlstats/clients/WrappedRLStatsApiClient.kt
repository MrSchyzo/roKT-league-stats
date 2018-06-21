package com.mrschyzo.api.rlstats.clients

import com.mrschyzo.api.rlstats.models.*

class WrappedRLStatsApiClient(
        private val statsClient: RLStatsAPIClient
) : RLClient
{
    override fun getPlatform(id: Int): Platform? = statsClient.getPlatform(id)

    override fun getPlaylist(id: Int): Playlist? = statsClient.getPlaylist(id)

    override fun getTier(id: Int): Tier? = statsClient.getTier(id)

    override fun getSeason(id: Int): Season? = statsClient.getSeason(id)


    override fun allPlatforms(): Collection<Platform> = statsClient.allPlatforms()

    override fun allPlaylists(): Collection<Playlist> = statsClient.allPlaylists()

    override fun allTiers(): Collection<Tier> = statsClient.allTiers()

    override fun allSeasons(): Collection<Season> = statsClient.allSeasons()


    override fun searchPlatforms(partialName: String): Collection<Platform> =
            statsClient.searchPlatforms(partialName)

    override fun searchPlaylists(partialName: String): Collection<Playlist> =
            statsClient.searchPlaylists(partialName)

    override fun searchTiers(partialName: String): Collection<Tier> =
            statsClient.searchTiers(partialName)


    override fun getPlayer(playerId: String, platform: Platform): Player? =
            statsClient.getPlayer(playerId, platform)

    override fun getPlayers(playersEntries: Collection<PlayerEntry>): Collection<Player> =
            statsClient.getPlayers(playersEntries)

    override fun getLeaderboardBy(playlist: Playlist): Collection<Player> =
            statsClient.getLeaderboardBy(playlist)

    override fun getLeaderboardBy(stat: Stat): Collection<Player> =
            statsClient.getLeaderboardBy(stat)

    override fun findPlayers(displayName: String): Collection<Player> =
            statsClient.findPlayers(displayName)

    override fun findPlayers(displayName: String, resultsLimit: Int): Collection<Player> =
            statsClient.findPlayers(displayName, resultsLimit)

    override fun findPlayers(displayName: String, criteria: (Player) -> Boolean): Collection<Player> =
            statsClient.findPlayers(displayName, criteria)


    override fun reloadData() = statsClient.reloadData()
}