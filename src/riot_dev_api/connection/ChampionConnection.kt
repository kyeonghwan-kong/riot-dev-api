package riot_dev_api.connection

import com.google.gson.Gson
import riot_dev_api.Global
import riot_dev_api.dto.champion_v3.ChampionDto
import riot_dev_api.dto.champion_v3.ChampionListDto

/**
 * Requests to this API are not counted against the application Rate Limits.
 */
class ChampionConnection : Connection {
    private val HOST: String
    private val URL_CHAMPIONS: String
    private val URL_BY_CHAMP_ID: String
    private val PARAM_FREE_TO_PLAY: String
    private val PARAM_API_KEY1: String
    private val PARAM_API_KEY2: String
    constructor(host: String) {
        this.HOST = host;
        this.URL_CHAMPIONS = "https://" + HOST + Global.ApiPath.CHAMPION_V3 + "champions"
        this.URL_BY_CHAMP_ID = "https://" + HOST + Global.ApiPath.CHAMPION_V3 + "champions/"
        this.PARAM_API_KEY1 = "?api_key="
        this.PARAM_API_KEY2 = "&api_key="
        this.PARAM_FREE_TO_PLAY = "?freeToPlay="
    }

    /**
     * get champion list.
     * @param freeToflay Optional filter param to retrieve only free to play champions.
     */
    public fun getChampionList(freeToflay: Boolean, apiKey: String): ChampionListDto? {
        val responde = connectAPI(URL_CHAMPIONS + PARAM_FREE_TO_PLAY + freeToflay + PARAM_API_KEY2 + apiKey,0 )
        if (responde.isNotEmpty()) {
            return Gson().fromJson(responde, ChampionListDto::class.java)
        }
        return null
    }

    /**
     * Get one champion by champion ID.
     */
    public fun getChampionByChampionID(championId: Long, apiKey: String): ChampionDto? {
        val responde = connectAPI(URL_BY_CHAMP_ID + championId + PARAM_API_KEY1 + apiKey, 0)
        if (responde.isNotEmpty()) {
            return Gson().fromJson(responde, ChampionDto::class.java)
        }
        return null
    }


}