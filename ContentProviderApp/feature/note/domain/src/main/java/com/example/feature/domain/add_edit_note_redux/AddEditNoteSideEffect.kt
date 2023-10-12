package com.example.feature.domain.add_edit_note_redux

import com.example.core.redux.SideEffect


sealed class AddEditNoteSideEffect: SideEffect {
    data class ShowSnackbar(val message: String): AddEditNoteSideEffect()
    object SaveNote: AddEditNoteSideEffect()
}
