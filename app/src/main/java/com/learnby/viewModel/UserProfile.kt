package com.learnby.viewModel

import android.provider.ContactsContract.CommonDataKinds.Email

data class UserProfile(
    val name: String,
    val email: Email,
    val avatarUrl: String
) {

}
