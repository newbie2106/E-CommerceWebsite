import React, { useContext, useState } from "react";
import { FaUser, FaLock, FaShoppingCart, FaGlobe, FaGoogle, FaFacebook } from "react-icons/fa";
import languages from "../configs/languages";
import { getCurrentUser, loginAccount } from "../configs/APIs";
import cookie from "react-cookies";
import { Link, useNavigate } from "react-router-dom";
import { MyDispatchContext, MyUserContext } from "../App";

const Login = () => {
    const user = useContext(MyUserContext);
    const translations = languages;
    const dispatch = useContext(MyDispatchContext);
    const nav = useNavigate();
    const [username, setUsername] = useState("");
    const [password, setPasssword] = useState("");
    const [language, setLanguage] = useState("vi");
    const [isLoading, setIsLoading] = useState(false);
    const [message, setMessage] = useState("");
    const handleSubmit = (evt) => {
        evt.preventDefault();
        setIsLoading(true);
        setMessage("")
        loginAccount(username, password)
            .then((res) => {
                cookie.save("token", res.data);
                getCurrentUser()
                    .then((res) => {
                        let user = res.data;
                        cookie.save("user", user);
                        dispatch({
                            type: "login",
                            payload: user,
                        });
                        setIsLoading(false);
                        nav("/");
                    })
                    .catch((ex) => {
                        console.error(ex);
                        setIsLoading(false);
                    });
            })
            .catch((ex) => {
                console.error(ex);
                setIsLoading(false);
                setMessage("Tài khoản hoặc mật khẩu không chính xác");
            });
    };

    const handleGoogleLogin = () => {
        // Thêm logic đăng nhập với Google
        console.log("Đăng nhập với Google");
    };

    const handleFacebookLogin = () => {
        // Thêm logic đăng nhập với Facebook
        console.log("Đăng nhập với Facebook");
    };

    const toggleLanguage = () => {
        setLanguage((prevLang) => (prevLang === "en" ? "vi" : "en"));
    };

    const t = translations[language];

    return (
        <div className="min-h-screen bg-gradient-to-r from-blue-400 to-purple-500 flex items-center justify-center px-4 sm:px-6 lg:px-8">
            <div className="max-w-md w-full space-y-8 bg-white p-6 rounded-xl shadow-2xl transition-all duration-300 transform hover:scale-105" style={{ width: '60%' }}>
                <div className="text-center">
                    <FaShoppingCart className="mx-auto h-12 w-12 text-indigo-600" />
                    <h2 className="mt-6 text-2xl font-extrabold text-gray-900">{t.welcome}</h2>
                    <p className="mt-2 text-sm text-gray-600">{t.signIn}</p>
                </div>
                <form className="mt-8 space-y-6" onSubmit={handleSubmit}>

                    {/* Nút đăng nhập với Google */}
                    <div>
                        <button
                            type="button"
                            onClick={handleGoogleLogin}
                            className="flex justify-center w-full py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-red-600 hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500 transition duration-150 ease-in-out"
                        >
                            <FaGoogle className="mr-2 h-5 w-5" />
                            {language === "en" ? "Sign in with Google" : "Đăng nhập với Google"}
                        </button>
                    </div>

                    {/* Nút đăng nhập với Facebook */}
                    <div>
                        <button
                            type="button"
                            onClick={handleFacebookLogin}
                            className="flex justify-center w-full py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 transition duration-150 ease-in-out"
                        >
                            <FaFacebook className="mr-2 h-5 w-5" />
                            {language === "en" ? "Sign in with Facebook" : "Đăng nhập với Facebook"}
                        </button>
                    </div>

                    {/* đăng nhập */}
                    <div className="rounded-md shadow-sm -space-y-px">
                        <div>
                            <label htmlFor="username" className="sr-only">{t.username}</label>
                            <div className="relative">
                                <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                                <FaUser className="h-5 w-5 text-gray-400" />

                                </div>
                                <input
                                    id="username"
                                    name="username"
                                    type="text"
                                    required
                                    className="appearance-none rounded-none relative block w-full px-3 py-2 pl-10 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                                    placeholder={t.username}
                                    value={username}
                                    onChange={(e) => setUsername(e.target.value)}
                                    disabled={isLoading}
                                />
                            </div>
                        </div>
                        <div>
                            <label htmlFor="password" className="sr-only">{t.password}</label>
                            <div className="relative">
                                <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                                    <FaLock className="h-5 w-5 text-gray-400" />
                                </div>
                                <input
                                    id="password"
                                    name="password"
                                    type="password"
                                    required
                                    className="appearance-none rounded-none relative block w-full px-3 py-2 pl-10 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                                    placeholder={t.password}
                                    value={password}
                                    onChange={(e) => setPasssword(e.target.value)}
                                    disabled={isLoading}
                                />
                            </div>
                        </div>
                        {message && (
                            <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mt-3" role="alert">
                                
                                <span className="block sm:inline"> {message}</span>
                            </div>
                        )}
                    </div>

                    <div className="flex items-center justify-between">
                        <div className="text-sm">
                            <Link to="/password-reset" className="font-medium text-indigo-600 hover:text-indigo-500">
                                {t.forgotPassword}
                            </Link>
                        </div>
                        <button
                            type="button"
                            onClick={toggleLanguage}
                            className="flex items-center text-sm font-medium text-indigo-600 hover:text-indigo-500"
                        >
                            <FaGlobe className="mr-1" />
                            {language === "en" ? "Tiếng Việt" : "English"}
                        </button>
                    </div>

                    <div>
                        <button
                            type="submit"
                            className={`group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white ${isLoading ? "bg-gray-400" : "bg-indigo-600"} ${!isLoading && "hover:bg-indigo-700"} focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 transition duration-150 ease-in-out`}
                            disabled={isLoading}
                        >
                            {isLoading ? "Đang đăng nhập..." : t.signInButton}
                        </button>
                    </div>

                    <div className="text-center">
                        <p className="mt-2 text-sm text-gray-600">
                            {t.noAccount}{" "}
                            <Link to="/register" className="font-medium text-indigo-600 hover:text-indigo-500">
                                {t.register}
                            </Link>
                        </p>
                    </div>
                </form>
            </div>
        </div>


    );
};

export default Login;
