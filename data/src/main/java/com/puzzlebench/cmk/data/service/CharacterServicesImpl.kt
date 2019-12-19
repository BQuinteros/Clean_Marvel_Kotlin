package com.puzzlebench.cmk.data.service

import android.util.Log
import com.puzzlebench.cmk.data.mapper.service.CharacterMapperService
import com.puzzlebench.cmk.domain.model.Character
import com.puzzlebench.cmk.domain.model.FullInfoCharacter
import com.puzzlebench.cmk.domain.service.CharacterServices
import io.reactivex.Single


class CharacterServicesImpl(private val api: MarvelResquestGenerator = MarvelResquestGenerator(), private val mapper: CharacterMapperService = CharacterMapperService()) : CharacterServices {
    override fun getCharacters(): Single<List<Character>> {
        return api.makeMarvelService().getCharacter().map { response ->
            response.data!!.result.map { characterResponse -> mapper.transform(characterResponse) }
        }
    }

    override fun getSingleCharacter(id: Int): Single<FullInfoCharacter> {
        Log.i(this.toString(),"AGS")
        var info = api.makeMarvelService().getSingleCharacter(id).map { res -> mapper.transformToSingleCharacter(res) }
        return api.makeMarvelService().getSingleCharacter(id).map { res -> mapper.transformToSingleCharacter(res)}
    }
}
