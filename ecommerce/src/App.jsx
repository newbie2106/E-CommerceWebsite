import { createContext, useReducer } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./components/Home";
import Header from "./components/Header";
import MyUserReducer from "./reducers/MyUserReducer";
import Login from "./components/Login";
import Register from "./components/Register";
import RegistrationForm from "./components/Register";
import OrderDetailsPage from "./components/Pay";
import ScrollToTopButton from "./components/ScrollToTopButton";
import ProductDetails from "./components/ProductDetails";
import Footer from "./components/Footer";
import Cart from "./components/Cart";
import cookie from "react-cookies";
import ChangePassword from "./components/ChangePassword";
import PasswordReset from "./components/PasswordReset";


export const MyUserContext = createContext();
export const MyDispatchContext = createContext();

const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, cookie.load("user") || null);
  return (
    <BrowserRouter>
      <MyUserContext.Provider value={user}>
        <MyDispatchContext.Provider value={dispatch}>
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



          </Routes>
          <Footer />

          <ScrollToTopButton />
        </MyDispatchContext.Provider>
      </MyUserContext.Provider>
    </BrowserRouter>
  );
};

export default App