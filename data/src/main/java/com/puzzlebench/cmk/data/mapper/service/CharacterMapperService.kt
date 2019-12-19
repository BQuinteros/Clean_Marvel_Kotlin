package com.puzzlebench.cmk.data.mapper.service

import com.puzzlebench.cmk.data.service.response.CharacterResponse
import com.puzzlebench.cmk.data.service.response.ComicsResponse
import com.puzzlebench.cmk.data.service.response.SingleCharacterResponse
import com.puzzlebench.cmk.data.service.response.ThumbnailResponse
import com.puzzlebench.cmk.domain.model.Character
import com.puzzlebench.cmk.domain.model.Comics
import com.puzzlebench.cmk.domain.model.FullInfoCharacter
import com.puzzlebench.cmk.domain.model.Thumbnail


open class CharacterMapperService : BaseMapperService<CharacterResponse, Character> {

    override fun transform(characterResponse: CharacterResponse): Character
            = Character(
            characterResponse.id,
            characterResponse.name,
            characterResponse.description,
            transformToThumbnail(characterResponse.thumbnail)
    )

    override fun transformToResponse(type: Character): CharacterResponse
            = CharacterResponse(
            type.id,
            type.name,
            type.description,
            transformToThumbnailResponse(type.thumbnail)
    )

    fun transformToThumbnail(thumbnailResponse: ThumbnailResponse): Thumbnail
            = Thumbnail(
            thumbnailResponse.path,
            thumbnailResponse.extension
    )

    fun transformToThumbnailResponse(thumbnail: Thumbnail): ThumbnailResponse
            = ThumbnailResponse(
            thumbnail.path,
            thumbnail.extension
    )

    fun transform(charactersResponse: List<CharacterResponse>): List<Character>
            = charactersResponse.map { transform(it) }

    fun transformToSingleCharacter(character: SingleCharacterResponse): FullInfoCharacter =
            FullInfoCharacter(
                    character.id,
                    character.name,
                    character.description,
                    transformToThumbnail(character.thumbnail),
                    transformToComics(character.comics))

    fun transformToComics(comicsResponse: ComicsResponse): Comics =
            Comics(
                    avalible = comicsResponse.available,
                    collectionURI = comicsResponse.collectionURI
            )
}