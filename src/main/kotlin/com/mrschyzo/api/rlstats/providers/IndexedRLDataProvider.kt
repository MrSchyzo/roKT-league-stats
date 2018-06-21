package com.mrschyzo.api.rlstats.providers

import com.mrschyzo.api.rlstats.models.*
import com.mrschyzo.api.rlstats.services.RLService
import retrofit2.Call

class IndexedRLDataProvider(
        private val service: RLService
) : RLDataProvider {

    private lateinit var platforms: Map<Int, Platform>

    private lateinit var playlists: Map<Int, Playlist>

    private lateinit var tiers: Map<Int, Tier>

    private lateinit var seasons: Map<Int, Season>

    init {
        reloadData()
    }

    private fun loadPlatforms(): Map<Int, Platform> = loadFromCall(service.getPlatforms(), "Getting platforms")

    private fun loadPlaylists(): Map<Int, Playlist> = loadFromCall(service.getPlaylists(), "Getting playlists")

    private fun loadTiers(): Map<Int, Tier> = loadFromCall(service.getTiers(), "Getting tiers")

    private fun loadSeasons(): Map<Int, Season> = loadFromCall(service.getSeasons(), "Getting seasons")

    private fun <K : WithId<Int>, T : Collection<K>> loadFromCall(serviceCall: Call<T>, message: String? = null) : Map<Int, K> =
            serviceCall.getResponseOrException(message).map { it -> it.id to it }.toMap()


    override fun getPlatform(id: Int) : Platform? = getFromId(platforms, id)

    override fun getPlaylist(id: Int) : Playlist? = getFromId(playlists, id)

    override fun getTier(id: Int) : Tier? = getFromId(tiers, id)

    override fun getSeason(id: Int) : Season? = getFromId(seasons, id)

    private fun <T> getFromId(map: Map<Int, T>, id: Int) : T? = map[id]


    override fun allPlatforms(): Collection<Platform> = getAll(platforms)

    override fun allPlaylists(): Collection<Playlist> = getAll(playlists)

    override fun allTiers(): Collection<Tier> = getAll(tiers)

    override fun allSeasons(): Collection<Season> = getAll(seasons)

    private fun <T> getAll(map: Map<*, T>) : Collection<T> = map.values


    override fun searchPlatforms(partialName: String): Collection<Platform> = searchEntities(platforms, partialName)

    override fun searchPlaylists(partialName: String): Collection<Playlist> = searchEntities(playlists, partialName)

    override fun searchTiers(partialName: String): Collection<Tier> = searchEntities(tiers, partialName)

    private fun <T: WithName> searchEntities(entities: Map<Int, T>, partialName: String) : Collection<T> =
        entities.values.filter { it -> it.name.contains(partialName, true) }


    override fun reloadData() {
        platforms = loadPlatforms()
        playlists = loadPlaylists()
        tiers = loadTiers()
        seasons = loadSeasons()
    }
}