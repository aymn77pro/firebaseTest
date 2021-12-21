package com.example.firebasetest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.firebasetest.databinding.FragmentListOfContactBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ListOfContact : Fragment() {
 private lateinit var auth: FirebaseAuth
 private lateinit var googleSignInClient: GoogleSignInClient

 private var _binding:FragmentListOfContactBinding ?= null
 private val binding get() = _binding


 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
 }

 override fun onCreateView(
  inflater: LayoutInflater,
  container: ViewGroup?,
  savedInstanceState: Bundle?
 ): View? {
   _binding = FragmentListOfContactBinding.inflate(inflater,container,false)
   return binding?.root
 }

 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  super.onViewCreated(view, savedInstanceState)
  auth = Firebase.auth
  binding?.textView?.text = "${auth.currentUser?.displayName}"
  val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
   .requestIdToken(getString(R.string.web_clint_id))
   .requestEmail().build()

  googleSignInClient = GoogleSignIn.getClient(context,gso)

  binding?.singOut?.setOnClickListener {
   singOut()
  }
 }
 private fun singOut(){
  Firebase.auth.signOut()
  googleSignInClient.signOut()
  val action = ListOfContactDirections.actionListOfContactToSinginFragment()
  findNavController().navigate(action)
  }
}