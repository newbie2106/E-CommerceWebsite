import React, { useState } from "react";
import { FaUser, FaLock, FaEye, FaEyeSlash } from "react-icons/fa";
import { requestOTP, verifyOtp, createNewPassword } from "../configs/APIs";
import { useNavigate } from "react-router-dom";

const PasswordReset = () => {
    const [step, setStep] = useState(1);
    const [username, setUsername] = useState("");
    const [otp, setOtp] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [showNewPassword, setShowNewPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);
    const [isButtonDisabled, setIsButtonDisabled] = useState(false);
    const [error, setError] = useState("");
    const [passwordStrength, setPasswordStrength] = useState(0);
    const nav = useNavigate();
    const [success, setSuccess] = useState(false);

    const validateUsername = async (e) => {
        setIsButtonDisabled(true);
        e.preventDefault();
        try {
            // Kiểm tra tên người dùng và gửi OTP
            console.log(username)
            await requestOTP(username);
            setStep(2);
            setError("");
        } catch (err) {
            setError("Tên người dùng không chính xác. Vui lòng thử lại.");
        } finally {
            setIsButtonDisabled(false); // Bật lại nút nếu cần (tuỳ thuộc vào logic bạn muốn)
        }
    };

    const validateOTP = async (e) => {
        e.preventDefault();
        setIsButtonDisabled(true);
        try {
            // Xác thực OTP
            await verifyOtp(otp, username);
            setStep(3);
            setError("");
        } catch (err) {
            setError("Mã OTP không hợp lệ. Vui lòng thử lại.");
        } finally {
            setIsButtonDisabled(false); // Bật lại nút nếu cần (tuỳ thuộc vào logic bạn muốn)
        }
    };


    const validatePasswords = async (e) => {
        e.preventDefault();
        if (!newPassword || !confirmPassword) {
            setError("Vui lòng điền đầy đủ các trường!");
            return;
        }

        if (newPassword !== confirmPassword) {
            setError("Mật khẩu mới và xác nhận mật khẩu không khớp!");
            return;
        }
        setIsButtonDisabled(true);
        try {
            const response = await createNewPassword(username, newPassword, confirmPassword);
            setSuccess(true); // Đặt trạng thái thành công
            setError(""); // Xóa thông báo lỗi

        } catch (error) {
            setError("Đổi mật khẩu không thành công");
        } finally {
            setIsButtonDisabled(false); // Bật lại nút nếu cần (tuỳ thuộc vào logic bạn muốn)
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
        nav("/");
    };

    const handleLogin = () => {
        nav("/login");
    };

    return (
        <div className="min-h-screen bg-gray-100 flex items-center justify-center">
            <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
                {step === 1 && (
                    <form onSubmit={validateUsername} className="space-y-4">
                        <h2 className="text-2xl font-bold text-center text-gray-800 mb-4">Đặt lại mật khẩu</h2>
                        <div className="relative">
                            <FaUser className="absolute top-3 left-3 text-gray-400" />
                            <input
                                type="text"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                                placeholder="Nhập tên người dùng của bạn"
                                className="w-full pl-10 pr-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                required
                            />
                        </div>
                        <button
                            type="submit"
                            disabled={isButtonDisabled}
                            className="w-full bg-blue-500 text-white py-2 rounded-md hover:bg-blue-600 transition duration-300"
                        >
                            Gửi OTP
                        </button>
                    </form>
                )}

                {step === 2 && (
                    <form onSubmit={validateOTP} className="space-y-4">
                        <h2 className="text-2xl font-bold text-center text-gray-800 mb-4">Nhập OTP</h2>
                        <input
                            type="text"
                            value={otp}
                            onChange={(e) => setOtp(e.target.value)}
                            placeholder="Nhập mã OTP 6 chữ số"
                            className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                            required
                        />
                        <button
                            type="submit"
                            disabled={isButtonDisabled}
                            className="w-full bg-blue-500 text-white py-2 rounded-md hover:bg-blue-600 transition duration-300"
                        >
                            Xác thực OTP
                        </button>
                    </form>
                )}

                {step === 3 && (
                    <>
                        {success ? (
                            <div className="p-4 bg-green-100 border border-green-400 text-green-700 rounded-lg">
                                <p className="font-semibold">Mật khẩu đã được thay đổi thành công!</p>
                                <div className="mt-4 flex justify-around">
                                    <button
                                        onClick={handleGoHome}
                                        className="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600"
                                    >
                                        Trang chủ
                                    </button>
                                    <button
                                        onClick={handleLogin}
                                        className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600"
                                    >
                                        Đăng nhập
                                    </button>
                                </div>
                            </div>
                        ) : (
                            <form onSubmit={validatePasswords} className="space-y-4">
                                <h2 className="text-2xl font-bold text-center text-gray-800 mb-4">Đặt mật khẩu mới</h2>
                                <div className="relative">
                                    <FaLock className="absolute top-3 left-3 text-gray-400" />
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
                                        onClick={() => setShowNewPassword(!showNewPassword)}
                                        className="absolute top-2 right-2 text-gray-400"
                                    >
                                        {showNewPassword ? <FaEyeSlash /> : <FaEye />}
                                    </button>
                                </div>
                                <div className="relative">
                                    <FaLock className="absolute top-3 left-3 text-gray-400" />
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
                                        value={confirmPassword}
                                        onChange={(e) => setConfirmPassword(e.target.value)}
                                        tabIndex={3}  // Focus khi nhấn Tab lần ba
                                    />
                                    <button
                                        type="button"
                                        onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                                        className="absolute top-2 right-2 text-gray-400"
                                    >
                                        {showConfirmPassword ? <FaEyeSlash /> : <FaEye />}
                                    </button>
                                </div>
                                <div className="w-full bg-gray-200 rounded-full h-2.5">
                                    <div
                                        className={`h-2.5 rounded-full ${getPasswordStrengthColor()}`}
                                        style={{ width: `${(passwordStrength / 5) * 100}%` }}
                                    ></div>
                                </div>
                                <button
                                    disabled={isButtonDisabled}
                                    type="submit"
                                    className="w-full bg-blue-500 text-white py-2 rounded-md hover:bg-blue-600 transition duration-300"
                                >
                                    Đặt lại mật khẩu
                                </button>
                            </form>
                        )}
                    </>
                )}
            </div>
        </div>
    );
};

export default PasswordReset;
