import { createContext, useReducer } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./components/Home";
import Header from "./components/Header";
import MyUserReducer from "./reducers/MyUserReducer";
import Login from "./components/Login";
import RegistrationForm from "./components/Register";
import OrderDetailsPage from "./components/Pay";
import ScrollToTopButton from "./components/ScrollToTopButton";
import ProductDetails from "./components/ProductDetails";
import Footer from "./components/Footer";
import Cart from "./components/Cart";
import cookie from "react-cookies";
import ChangePassword from "./components/ChangePassword";
import PasswordReset from "./components/PasswordReset";
import UpdateUserForm from "./components/UpdateUserForm";
import ProfilePage from "./components/Profile";
import AddressPage from "./components/AddressPage";
import AddShippingAddress from "./components/AddShippingAddress";
import { GoogleOAuthProvider } from "@react-oauth/google";


export const MyUserContext = createContext();
export const MyDispatchContext = createContext();

const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, cookie.load("user") || null);
  
  return (
    <BrowserRouter>
      <MyUserContext.Provider value={user}>
        <MyDispatchContext.Provider value={dispatch}>
          <GoogleOAuthProvider clientId="240727462343-2jbo7vsaicmuqr65dd4jmu2099m90if9.apps.googleusercontent.com">
            <Header />
            <Routes>

              <Route path="/" element={<Home />} />
              <Route path="/login" element={<Login />} />
              <Route path="/register" element={<RegistrationForm />} />
              <Route path="/pay" element={<OrderDetailsPage />} />
              <Route path="/product/:productId" element={<ProductDetails />} />
              <Route path="/cart" element={<Cart />} />
              <Route path="/change-password" element={<ChangePassword />} />
              <Route path="/password-reset" element={<PasswordReset />} />
              <Route path="/edit-profile" element={<UpdateUserForm />} />
              <Route path="/profile" element={<ProfilePage />} />
              <Route path="/address" element={<AddressPage />} />
              <Route path="/add-address" element={<AddShippingAddress />} />



            </Routes>
            <Footer />

            <ScrollToTopButton />
          </GoogleOAuthProvider>
        </MyDispatchContext.Provider>
      </MyUserContext.Provider>
    </BrowserRouter>
  );
};

export default App