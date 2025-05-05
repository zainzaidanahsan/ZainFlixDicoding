package com.capstone.favorite.di

import android.content.Context
import com.capstone.favorite.ui.FavoriteFragment
import com.capstone.zainflix.di.FavoriteModule
import dagger.BindsInstance
import dagger.Component



@Component(dependencies = [FavoriteModule::class])
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun appDepedencies(favoriteModule: FavoriteModule) : Builder
        fun build() : FavoriteComponent
    }
}