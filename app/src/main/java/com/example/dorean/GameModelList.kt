package com.example.dorean

import android.util.Log
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


object GameModelList {

    val games = mutableListOf<GameModel>()

    fun getGameData(json: JsonObject) {
        val store = json.getAsJsonObject("data")
            .getAsJsonObject("Catalog")
            .getAsJsonObject("searchStore")

        val count = store.getAsJsonObject("paging").get("total").asInt
        val gameElements = store.getAsJsonArray("elements")

        Log.d("DATA_COUNT", "count $count")
        Log.d("TEST", gameElements.toString())
        setGameModelList(gameElements,count)
    }

    private fun setGameModelList(elements:JsonArray, size: Int) {
        for (i in 0 until size) {

            //reversing the list cause the free games are on the end of the list so getting
            //results faster
            val index = size - 1 - i
            val game = elements[index].asJsonObject


            if (isFreeToRedeem(game)) {
                Log.d("LOOP", game.get("title").asString)
                val endDate = game.getAsJsonObject("promotions")
                    .getAsJsonArray("promotionalOffers")[0].asJsonObject
                    .getAsJsonArray("promotionalOffers")[0].asJsonObject.get("endDate").asString


                val gameModel = GameModel(
                    name = game.get("title").asString,
                    description = game.get("description").asString,
                    endDate = isoToStringDate(endDate)
                )
                games.add(gameModel)

            }
        }
    }

    private fun isFreeToRedeem(game: JsonObject): Boolean {
        //check for promotions
        if (!game.get("promotions").isJsonNull){
            val promotions = game.getAsJsonObject("promotions")
            val offers = promotions.getAsJsonArray("promotionalOffers")

            if (offers.size() != 0){
                val discount = offers[0].asJsonObject
                    .getAsJsonArray("promotionalOffers")[0].asJsonObject
                        .getAsJsonObject("discountSetting").get("discountPercentage").asInt

                return discount == 0
            }
        }   
        return false
    }

    private fun isoToStringDate(isoDate: String): String {

        val dateTime = ZonedDateTime.parse(isoDate).withZoneSameInstant(ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a", Locale.getDefault())
        val localizedDate = dateTime.format(formatter)
        return localizedDate
    }
}