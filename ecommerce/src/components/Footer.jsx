import React from "react";
import { FaFacebook, FaTwitter, FaInstagram, FaLinkedin } from "react-icons/fa";
import { Link } from "react-router-dom"; // Nhập Link từ React Router

const Footer = () => {
    return (
        <footer className="bg-gray-800 text-white py-12">
            <div className="container mx-auto px-4">
                <div className="grid grid-cols-1 md:grid-cols-4 gap-8 mb-8">
                    <div>
                        <h2 className="text-2xl font-bold mb-4">Company Name</h2>
                        <p className="text-gray-300">
                            "Chào mừng đến với TTH SHOP, nơi cung cấp những sản phẩm chất lượng cao và dịch vụ tận tâm, giúp bạn trải nghiệm mua sắm trực tuyến dễ dàng và thuận tiện nhất."
                        </p>
                    </div>
                    <div>
                        <h3 className="text-xl font-semibold mb-4">Help</h3>
                        <ul className="space-y-2">
                            <li>
                                <Link to="/faq" className="hover:text-gray-300 transition duration-300">FAQ</Link>
                            </li>
                            <li>
                                <Link to="/support" className="hover:text-gray-300 transition duration-300">Customer Support</Link>
                            </li>
                            <li>
                                <Link to="/contact" className="hover:text-gray-300 transition duration-300">Contact Us</Link>
                            </li>
                        </ul>
                    </div>
                    <div>
                        <h3 className="text-xl font-semibold mb-4">Resources</h3>
                        <ul className="space-y-2">
                            <li>
                                <Link to="/blog" className="hover:text-gray-300 transition duration-300">Blog</Link>
                            </li>
                            <li>
                                <Link to="/whitepapers" className="hover:text-gray-300 transition duration-300">Whitepapers</Link>
                            </li>
                            <li>
                                <Link to="/case-studies" className="hover:text-gray-300 transition duration-300">Case Studies</Link>
                            </li>
                        </ul>
                    </div>
                    <div>
                        <h3 className="text-xl font-semibold mb-4">Extra Links</h3>
                        <ul className="space-y-2">
                            <li>
                                <Link to="/about" className="hover:text-gray-300 transition duration-300">About Us</Link>
                            </li>
                            <li>
                                <Link to="/careers" className="hover:text-gray-300 transition duration-300">Careers</Link>
                            </li>
                            <li>
                                <Link to="/privacy-policy" className="hover:text-gray-300 transition duration-300">Privacy Policy</Link>
                            </li>
                            <li>
                                <Link to="/terms-of-service" className="hover:text-gray-300 transition duration-300">Terms of Service</Link>
                            </li>
                        </ul>
                    </div>
                </div>
                <div className="flex flex-col md:flex-row justify-between items-center border-t border-gray-700 pt-8">
                    <div className="flex space-x-4 mb-4 md:mb-0">
                        <Link to="#" className="text-2xl hover:text-gray-300 transition duration-300">
                            <FaFacebook />
                        </Link>
                        <Link to="#" className="text-2xl hover:text-gray-300 transition duration-300">
                            <FaTwitter />
                        </Link>
                        <Link to="#" className="text-2xl hover:text-gray-300 transition duration-300">
                            <FaInstagram />
                        </Link>
                        <Link to="#" className="text-2xl hover:text-gray-300 transition duration-300">
                            <FaLinkedin />
                        </Link>
                    </div>
                    <p className="text-gray-300 text-sm">
                        &copy; {new Date().getFullYear()} TTH SHOP. All rights reserved.
                    </p>
                </div>
            </div>
        </footer>
    );
};

export default Footer;
