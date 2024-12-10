import { initializeApp } from "firebase/app";
import { getFirestore } from "firebase/firestore";
import { getAuth, FacebookAuthProvider } from 'firebase/auth';
import firebase from "firebase/compat/app";

const firebaseConfig = {
    apiKey: "AIzaSyCwTdqeziiNWZtsbyRLIhOa110Yxan3Ohk",
    authDomain: "ecommerce-faecf.firebaseapp.com",
    projectId: "ecommerce-faecf",
    storageBucket: "ecommerce-faecf.appspot.com",
    messagingSenderId: "227536363505",
    appId: "1:227536363505:web:bb4531ac10772584e2ae50",
    measurementId: "G-ZVWBZS0BD6"
  };
// Initialize Firebase
const app = initializeApp(firebaseConfig);
const db = getFirestore(app);
const auth = getAuth(app);

export { db, auth, app }
export const fbProvider = new FacebookAuthProvider();
fbProvider.addScope('public_profile');
fbProvider.addScope('email');

export default firebase;