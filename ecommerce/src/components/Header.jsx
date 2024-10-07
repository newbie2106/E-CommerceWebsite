import React, { useContext, useEffect, useState } from "react";
import {
  FaSearch, FaShoppingCart, FaMapMarkerAlt, FaUser,
  FaChevronDown, FaPhoneAlt, FaSignOutAlt, FaEdit,
  FaKey, FaArrowLeft,
  FaChevronLeft,
  FaChevronRight
} from "react-icons/fa"; // Thêm các biểu tượng mới
import { Link, useNavigate } from "react-router-dom";
import { MyDispatchContext, MyUserContext } from "../App";
import { loadCategories } from "../configs/APIs";
import { FaDiagramNext } from "react-icons/fa6";
import { SlArrowRight } from "react-icons/sl";
import { BsArrowRight } from "react-icons/bs";

const Header = () => {
  const user = useContext(MyUserContext);
  const dispatch = useContext(MyDispatchContext);
  const [categories, setCategories] = useState([]);
  const [isDropdownOpen, setDropdownOpen] = useState(false);
  const [isEditProfileMenu, setIsEditProfileMenu] = useState(false); // State để chuyển menu
  const [q, setQ] = useState("");
  const nav = useNavigate();

  const toggleDropdown = () => {
    setDropdownOpen(!isDropdownOpen);
  };
  const searchCategory = (e, cateId) => {
    e.preventDefault();
    navigate(`/?cateId=${cateId}`);  // Điều hướng với cateId
  };
  const handleLogout = () => {
    dispatch({ type: "logout" });
  };

  const submit = (e) => {
    e.preventDefault();
    nav(`/?kw=${q}`);
  };

  useEffect(() => {
    loadCategories().then(res => {
      setCategories(res.data);
    });
  }, []);

  return (
    <>
      <header className="bg-gray-800 shadow-md">
        <div className="container mx-auto px-4">
          <div className="flex items-center justify-between py-4">
            {/* Left Section */}
            <div className="flex items-center">
              <img src="/resources/images/logo.png" alt="Company Logo" className="h-12 w-auto mr-4 rounded-full" />
              <Link to="/" className="m-3 text-white hover:text-gray-300 transition duration-300">
                Trang chủ
              </Link>
              <div className="flex items-center space-x-2 text-white">
                <FaPhoneAlt />
                <Link to="/call" className="hover:text-gray-300 transition duration-300">
                  Gọi mua hàng 1800.0138
                </Link>
              </div>
            </div>

            {/* Search Section */}
            <div className="mx-4">
              <form onSubmit={submit} className="mx-4">
                <div className="relative">
                  <input
                    type="text"
                    value={q}
                    onChange={e => setQ(e.target.value)}
                    placeholder="Bạn tìm gì..."
                    className="w-full bg-white border border-gray-300 rounded-full py-2 px-4 pr-10 focus:outline-none focus:ring-2 focus:ring-blue-300"
                  />
                  <button
                    type="submit"
                    className="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-500 hover:text-blue-700"
                  >
                    <FaSearch />
                  </button>
                </div>
              </form>
            </div>

            {/* Right Section */}
            <div className="flex items-center space-x-4">
              {user ? (
                <>
                  <div className="relative">
                    <button
                      onClick={toggleDropdown}
                      className="text-white flex items-center space-x-2 hover:text-gray-300 transition duration-300"
                    >
                      <img src={user.avatar} alt="Avatar" className="w-8 h-8 rounded-full" />
                      <span>{user.firstName} {user.lastName}</span>
                      <FaChevronDown />
                    </button>

                    {/* Dropdown Menu */}
                    {isDropdownOpen && (
                      <div className="absolute right-0 mt-2 w-48 bg-white rounded-md shadow-lg py-2 z-50">
                        {!isEditProfileMenu ? (
                          <>
                            <Link
                              to="/profile"
                              className="flex items-center px-4 py-2 text-gray-800 hover:bg-gray-100"
                            >
                              <FaUser className="mr-2" />
                              Trang cá nhân
                            </Link>
                            <button
                              onClick={() => setIsEditProfileMenu(true)} // Chuyển sang menu "Thay đổi hồ sơ"
                              className="flex items-center justify-between w-full text-left px-4 py-2 text-gray-800 hover:bg-gray-100"
                            >
                              <div className="flex items-center">
                                <FaEdit className="mr-2" />
                                Thay đổi hồ sơ
                              </div>
                              <FaChevronRight className="text-black" /> {/* Màu xám cho icon */}
                            </button>

                            <button
                              onClick={handleLogout}
                              className="flex items-center w-full text-left px-4 py-2 text-gray-800 hover:bg-gray-100"
                            >
                              <FaSignOutAlt className="mr-2" />
                              Đăng xuất
                            </button>
                          </>
                        ) : (
                          <>
                            <button
                              onClick={() => setIsEditProfileMenu(false)} // Quay lại menu gốc
                              className="flex items-center px-4 py-2 text-gray-800 hover:bg-gray-100"
                            >
                              <FaArrowLeft className="mr-2" />
                              Quay lại
                            </button>
                            <Link
                              to="/edit-profile"
                              className="flex items-center px-4 py-2 text-gray-800 hover:bg-gray-100"
                            >
                              <FaEdit className="mr-2" />
                              Thay đổi thông tin
                            </Link>
                            <Link
                              to="/change-password"
                              className="flex items-center px-4 py-2 text-gray-800 hover:bg-gray-100"
                            >
                              <FaKey className="mr-2" />
                              Thay đổi mật khẩu
                            </Link>
                            <Link
                              to="/address"
                              className="flex items-center px-4 py-2 text-gray-800 hover:bg-gray-100"
                            >
                              <FaMapMarkerAlt className="mr-2" />
                              Địa chỉ
                            </Link>
                          </>
                        )}
                      </div>
                    )}
                  </div>
                  <Link to="/cart" className="text-white hover:text-gray-300 transition duration-300 relative">
                    <FaShoppingCart />
                  </Link>
                  <Link to="/location" className="text-white hover:text-gray-300 transition duration-300">
                    <FaMapMarkerAlt />
                  </Link>
                </>
              ) : (
                <>
                  <Link to="/login" className="text-white hover:text-gray-300 transition duration-300">
                    Đăng nhập <FaUser />
                  </Link>
                  <Link to="/cart" className="text-white hover:text-gray-300 transition duration-300 relative">
                    <FaShoppingCart />
                  </Link>
                  <Link to="/location" className="text-white hover:text-gray-300 transition duration-300">
                    <FaMapMarkerAlt />
                  </Link>
                </>
              )}
            </div>
          </div>
        </div>
      </header>

      {/* Categories Section */}
      <nav className="bg-slate-500 shadow-md">
        <div className="container mx-auto px-4">
          <div className="flex space-x-6 py-3">
            {categories.map(category => (
              <Link
                key={category.id}
                href="#"
                className="text-white hover:text-gray-300 transition duration-300"
                onClick={e => searchCategory(e, category.id)}
              >
                {category.name}
              </Link>
            ))}
          </div>
        </div>
      </nav>
    </>
  );
};

export default Header;
