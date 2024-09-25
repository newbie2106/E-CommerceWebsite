import React, { useContext, useState } from "react";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import { changePassword } from "../configs/APIs";
import { MyDispatchContext, MyUserContext } from "../App";
import { useNavigate } from "react-router-dom";

const ChangePassword = () => {
    const [currentPassword, setCurrentPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [confirmNewPassword, setConfirmNewPassword] = useState("");
    const [showCurrentPassword, setShowCurrentPassword] = useState(false);
    const [showNewPassword, setShowNewPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);
    const [passwordStrength, setPasswordStrength] = useState(0);
    const [message, setMessage] = useState("");
    const [success, setSuccess] = useState(false); // State để theo dõi trạng thái thành công
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);
    const nav = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Kiểm tra nếu các trường nhập liệu không hợp lệ
        if (!currentPassword || !newPassword || !confirmNewPassword) {
            setMessage("Vui lòng điền đầy đủ các trường!");
            return;
        }

        if (newPassword !== confirmNewPassword) {
            setMessage("Mật khẩu mới và xác nhận mật khẩu không khớp!");
            return;
        }
        try {
            if (user) {
                const response = await changePassword(user.username, currentPassword, newPassword, confirmNewPassword);
                setSuccess(true); // Đặt trạng thái thành công
                setMessage(""); // Xóa thông báo lỗi
            }

        } catch (error) {
            setMessage("Đổi mật khẩu không thành công");
        }

    };

    const calculatePasswordStrength = (password) => {
        let strength = 0;
        if (password.length >= 8) strength++;
        if (password.match(/[a-z]+/)) strength++;
        if (password.match(/[A-Z]+/)) strength++;
        if (password.match(/[0-9]+/)) strength++;
        if (password.match(/[^a-zA-Z0-9]+/)) strength++;
        return strength;
    };

    const handleNewPasswordChange = (e) => {
        const newPass = e.target.value;
        setNewPassword(newPass);
        setPasswordStrength(calculatePasswordStrength(newPass));
    };

    const getPasswordStrengthColor = () => {
        switch (passwordStrength) {
            case 0:
            case 1:
                return "bg-red-500";
            case 2:
            case 3:
                return "bg-yellow-500";
            case 4:
            case 5:
                return "bg-green-500";
            default:
                return "bg-gray-300";
        }
    };

    const handleGoHome = () => {
        nav("/home");
    };

    const handleLogout = () => {
        dispatch({ type: "logout" });
        nav("/login");
    };
    return (
        <div className="min-h-screen bg-gray-100 flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
            <div className="max-w-md w-full space-y-8 bg-white p-10 rounded-xl shadow-md">
                <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
                    Đổi Mật Khẩu
                </h2>
                {success ? (
                    <div className="p-4 bg-green-100 border border-green-400 text-green-700 rounded-lg">
                        <p className="font-semibold">Mật khẩu đã được thay đổi thành công!</p>
                        <div className="mt-4 flex justify-around">
                            <button
                                onClick={handleLogout}
                                className="px-4 py-2 bg-red-500 text-white rounded-md hover:bg-red-600"
                            >
                                Thoát
                            </button>
                            <button
                                onClick={handleGoHome}
                                className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600"
                            >
                                Trang chủ
                            </button>
                        </div>
                    </div>
                ) : (
                    <form className="mt-8 space-y-6" onSubmit={handleSubmit}>
                        <div className="rounded-md shadow-sm -space-y-px">
                            {/* Mật khẩu hiện tại */}
                            <div className="relative">
                                <label htmlFor="current-password" className="sr-only">
                                    Mật khẩu hiện tại
                                </label>
                                <input
                                    id="current-password"
                                    name="current-password"
                                    type={showCurrentPassword ? "text" : "password"}
                                    required
                                    className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                                    placeholder="Mật khẩu hiện tại"
                                    value={currentPassword}
                                    onChange={(e) => setCurrentPassword(e.target.value)}
                                    tabIndex={1}  // Focus khi nhấn Tab lần đầu tiên
                                    autoFocus  // Tự động focus vào ô đầu tiên khi trang tải
                                />
                                <button
                                    type="button"
                                    className="absolute inset-y-0 right-0 pr-3 flex items-center text-sm leading-5"
                                    onClick={() => setShowCurrentPassword(!showCurrentPassword)}
                                    tabIndex={4}
                                >
                                    {showCurrentPassword ? <FaEyeSlash /> : <FaEye />}
                                </button>
                            </div>

                            {/* Mật khẩu mới */}
                            <div className="relative">
                                <label htmlFor="new-password" className="sr-only">
                                    Mật khẩu mới
                                </label>
                                <input
                                    id="new-password"
                                    name="new-password"
                                    type={showNewPassword ? "text" : "password"}
                                    required
                                    className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                                    placeholder="Mật khẩu mới"
                                    pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                    title="Mật khẩu phải chứa ít nhất một số, một chữ hoa, một chữ thường và ít nhất 8 ký tự"
                                    value={newPassword}
                                    onChange={handleNewPasswordChange}
                                    tabIndex={2}  // Focus khi nhấn Tab lần hai
                                />
                                <button
                                    type="button"
                                    className="absolute inset-y-0 right-0 pr-3 flex items-center text-sm leading-5"
                                    onClick={() => setShowNewPassword(!showNewPassword)}
                                    tabIndex={5}
                                >
                                    {showNewPassword ? <FaEyeSlash /> : <FaEye />}
                                </button>
                            </div>

                            {/* Xác nhận mật khẩu mới */}
                            <div className="relative">
                                <label htmlFor="confirm-new-password" className="sr-only">
                                    Xác nhận mật khẩu mới
                                </label>
                                <input
                                    id="confirm-new-password"
                                    name="confirm-new-password"
                                    type={showConfirmPassword ? "text" : "password"}
                                    required
                                    className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                                    placeholder="Xác nhận mật khẩu mới"
                                    value={confirmNewPassword}
                                    onChange={(e) => setConfirmNewPassword(e.target.value)}
                                    tabIndex={3}  // Focus khi nhấn Tab lần ba
                                />
                                <button
                                    type="button"
                                    className="absolute inset-y-0 right-0 pr-3 flex items-center text-sm leading-5"
                                    onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                                    tabIndex={6}
                                >
                                    {showConfirmPassword ? <FaEyeSlash /> : <FaEye />}
                                </button>
                            </div>
                        </div>

                        <div className="w-full bg-gray-200 rounded-full h-2.5">
                            <div
                                className={`h-2.5 rounded-full ${getPasswordStrengthColor()}`}
                                style={{ width: `${(passwordStrength / 5) * 100}%` }}
                            ></div>
                        </div>

                        {message && <p className="mt-4 text-red-500">{message}</p>}

                        <div>
                            <button
                                type="submit"
                                className="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                                tabIndex={7}  // Tab để đến nút submit cuối cùng
                            >
                                Đổi mật khẩu
                            </button>
                        </div>
                    </form>
                )}
            </div>
        </div>
    );

};

export default ChangePassword;
