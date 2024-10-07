import { useContext, useEffect, useState } from "react";
import { FiEye, FiFrown, FiShoppingCart } from "react-icons/fi";
import APIs, { addRecentlyViewedByUser, addToCart, endpoints, fetchCartItems, getRecentlyViewed, loadBrands, loadCategories } from "../configs/APIs";
import { useNavigate, useSearchParams } from "react-router-dom";
import { FaChevronLeft, FaChevronRight, FaTags } from "react-icons/fa";
import LoadingAnimations from "../components/LoadingAnimations"; // Điều chỉnh đường dẫn nếu cần
import VNDCurrencyFormat from "../configs/Utils";
import { MyUserContext } from "../App";
import axios from "axios";
import AddToCartNotification from "./AddToCartNotification";

const Home = () => {

  const slides = [
    {
      id: 1,
      image: "/resources/images/slide1.webp"
    },
    {
      id: 2,
      image: "/resources/images/slide2.webp"
    },
    {
      id: 3,
      image: "/resources/images/slide3.webp"
    }
  ];

  const [recentlyViewed, setRecentlyViewed] = useState([]);
  const [loading, setLoading] = useState(true);
  const user = useContext(MyUserContext);

  const [categories, setCategories] = useState([]);
  const [brands, setBrands] = useState([]);
  const [products, setProducts] = useState([]);
  const [page, setPage] = useState(1);
  const [priceRange, setPriceRange] = useState(null);
  const [q,] = useSearchParams();
  const [showNotification, setShowNotification] = useState(false);
  const navigate = useNavigate();
  const quantity = 1;
  // TEST THU 
  const loadRecentlyViewed = async () => {
    if(user && user.username){
      
      //const res = await axios.get(`http://localhost:8080/EcommerceWebsite/api/recently-viewed/user/${user.username}/`);
      const res = await getRecentlyViewed(user.username);
      setRecentlyViewed(res.data);
    }
  };

  useEffect(() => {
    if(user && user.username){
      
      loadRecentlyViewed();
    }
  }, [user]);


  const addRecentlyViewed = async (productId) => {
    //await axios.post(`http://localhost:8080/EcommerceWebsite/api/recently-viewed/add/?username=${user.username}&productId=${productId}`);
    if(user && user.username){
      
      await addRecentlyViewedByUser(user.username, productId);
      loadRecentlyViewed(); // Cập nhật danh sách đã xem
    }
  };

  const loadViewProducts = async () => {
    setLoading(true); // Bắt đầu tải dữ liệu
    try {
      let url = `${endpoints['products']}`;
      const params = new URLSearchParams();
      let cateId = q.get('cateId');
      if (cateId) {
        setPage(1);
        params.append('cateId', cateId);
        // url = `${url}?cateId=${cateId}`;
      }

      let brandId = q.get('brandId');
      if (brandId) {
        setPage(1);
        params.append('brandId', brandId);
        // url = `${url}?brandId=${brandId}`;
      }

      let kw = q.get("kw");
      if (kw) {
        params.append('kw', kw);
        setPage(1);
        // url = `${url}?kw=${kw}`;
        // console.log(url);
      }
      if (priceRange) {
        params.append('fromPrice', priceRange[0]);
        params.append('toPrice', priceRange[1]);
      }
      if (params.toString()) {
        url = `${url}?${params.toString()}`;
      }

      const res = await APIs.get(url);
      console.log(res);

      if (page === 1) {
        setProducts(res.data);
      } else {
        setProducts((current) => {
          return [...current, ...res.data];
        });
      }
    } catch (ex) {
      console.error(ex);
    } finally {
      setLoading(false); // Kết thúc tải dữ liệu
    }
  };

  // ===== HANDLE 
  const handleProductClick = async (productId) => {
    if(user && user.username){
      await addRecentlyViewed(productId);
    }
    navigate(`/product/${productId}`); // Điều hướng đến trang chi tiết sản phẩm
  };
  useEffect(() => {
    loadViewProducts();
  }, [q, page, priceRange]);

  const handleBrandClick = (brandId) => {
    if (brandId === null) {
      navigate(`?cateId=${q.get('cateId')}`);
    } else {
      navigate(`?cateId=${q.get('cateId')}&brandId=${brandId}`);

    }
    setPage(1); // Reset page to 1 when filtering
  };

  const handlePriceFilter = (range) => {
    switch (range) {
      case "0-2000000":
        setPriceRange([0, 2000000]);
        break;
      case "2000000-4000000":
        setPriceRange([2000000, 4000000]);
        break;
      case "4000000-7000000":
        setPriceRange([4000000, 7000000]);
        break;
      case "7000000-13000000":
        setPriceRange([7000000, 13000000]);
        break;
      case "13000000-20000000":
        setPriceRange([13000000, 20000000]);
        break;
      case "20000000-100000000":
        setPriceRange([20000000, 100000000]);
        break;
      case "all": // Trường hợp bỏ lọc
        setPriceRange(null);
        break;
      default:
        setPriceRange(null);
    }
    setPage(1); // Reset page to 1 when filtering
  };
  const handleAddToCart = async (productId, quantity) => {
    await addToCart(user.username, productId, quantity);
    fetchCartItems(user.username);
    setShowNotification(true);

  };
  const handleGoBack = () => {
    setShowNotification(false); // Đóng thông báo
  };

  const handleViewCart = () => {
    navigate("/cart"); // Chuyển hướng đến giỏ hàng
  };


  // ===== END HANDLE 

  useEffect(() => {
    loadCategories().then(res => {
      setCategories(res.data)
    })
  }, []);

  useEffect(() => {
    loadBrands().then(res => {
      setBrands(res.data)
    })
  }, []);

  // useEffect(() => {
  //   const fetchData = async () => {
  //     try {
  //       const viewedProducts = products.slice(0, 2);
  //       setRecentlyViewed(viewedProducts);
  //     } catch (err) {
  //       console.error(err);
  //     }
  //   };
  //   fetchData();
  // }, []);


  const [currentSlide, setCurrentSlide] = useState(0);
  const nextSlide = () => {
    setCurrentSlide((prev) => (prev === slides.length - 1 ? 0 : prev + 1));
  };

  const prevSlide = () => {
    setCurrentSlide((prev) => (prev === 0 ? slides.length - 1 : prev - 1));
  };

  return (
    <div className="container">
      {showNotification && (
        <AddToCartNotification
          onGoBack={handleGoBack}
          onViewCart={handleViewCart} // Xử lý khi nhấn vào xem giỏ hàng
        />
      )}
      <div className="bg-blue-50 min-h-screen p-8">
        <div className="relative overflow-hidden rounded-lg shadow-lg mb-8" style={{ height: "400px" }}>
          {slides.map((slide, index) => (
            <div
              key={slide.id}
              className={`absolute top-0 left-0 w-full h-full transition-opacity duration-500 ease-in-out ${index === currentSlide ? "opacity-100" : "opacity-0"}`}
              style={{ backgroundImage: `url(${slide.image})`, backgroundSize: "cover", backgroundPosition: "center" }}
            />
          ))}
          <button
            onClick={prevSlide}
            className="absolute top-1/2 left-4 transform -translate-y-1/2 bg-white bg-opacity-50 rounded-full p-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
            aria-label="Previous slide"
          >
            <FaChevronLeft className="text-gray-800 text-2xl" />
          </button>
          <button
            onClick={nextSlide}
            className="absolute top-1/2 right-4 transform -translate-y-1/2 bg-white bg-opacity-50 rounded-full p-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
            aria-label="Next slide"
          >
            <FaChevronRight className="text-gray-800 text-2xl" />
          </button>
        </div>

        {loading ? (
          <LoadingAnimations /> // Hiển thị Loading khi đang tải dữ liệu
        ) : (
          <>
            {q.get("cateId") ? (
              <>
                <div className="container mx-auto px-4">
                  <h2 className="text-3xl font-bold text-gray-800 mb-6">Danh sách các thương hiệu</h2>
                  <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-8 gap-4">
                    <div
                      className="p-4 bg-white shadow-md rounded-lg text-center cursor-pointer"
                      onClick={() => handleBrandClick(null)} // Gọi hàm với null để bỏ lọc
                    >
                      <FaTags className="text-gray-700 text-2xl mb-2" />
                      <span className="font-semibold text-gray-700">Tất cả</span>
                    </div>
                    {brands.map((brand) => (
                      <div
                        key={brand.id}
                        className="p-4 bg-white shadow-md rounded-lg text-center cursor-pointer"
                        onClick={() => handleBrandClick(brand.id)}
                      >
                        <img
                          src={brand.logo}
                          alt={brand.name}
                          className="w-full h-5 object-contain mb-2"
                        />
                        <span className="font-semibold text-gray-700">{brand.name}</span>
                      </div>
                    ))}
                  </div>
                  <div className="mt-4">
                    <h3 className="text-lg font-bold text-gray-800 mb-2">Lọc theo giá:</h3>
                    <div className="flex space-x-2">
                      <button onClick={() => handlePriceFilter("0-2000000")} className="bg-white border rounded-lg px-2 py-1 hover:bg-slate-100">Dưới 2 triệu</button>
                      <button onClick={() => handlePriceFilter("2000000-4000000")} className="bg-white border rounded-lg px-2 py-1 hover:bg-slate-100">2-4 triệu</button>
                      <button onClick={() => handlePriceFilter("4000000-7000000")} className="bg-white border rounded-lg px-2 py-1 hover:bg-slate-100">4-7 triệu</button>
                      <button onClick={() => handlePriceFilter("7000000-13000000")} className="bg-white border rounded-lg px-2 py-1 hover:bg-slate-100">7-13 triệu</button>
                      <button onClick={() => handlePriceFilter("13000000-20000000")} className="bg-white border rounded-lg px-2 py-1 hover:bg-slate-100">13-20 triệu</button>
                      <button onClick={() => handlePriceFilter("20000000-100000000")} className="bg-white border rounded-lg px-2 py-1 hover:bg-slate-100">Trên 20 triệu</button>
                      <button onClick={() => handlePriceFilter("all")} className="bg-red-500 text-white border rounded-lg px-2 py-1 hover:bg-red-600">Bỏ lọc theo giá</button>
                    </div>
                  </div>
                </div>
                {categories.find((category) => category.id === parseInt(q.get("cateId"))) && (
                  <h2 className="text-3xl font-bold text-gray-800 mb-6 mt-6">
                    {categories.find((category) => category.id === parseInt(q.get("cateId"))).name}
                  </h2>
                )}
                {products.length > 0 ? (
                  <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
                    {products.map((product) => (
                      <div
                        key={product.id}
                        className="bg-white rounded-lg shadow-lg overflow-hidden transition-transform duration-300 hover:scale-105"
                      >
                        <img
                          src={product.imageUrls[0]}
                          alt={product.name}
                          className="w-full h-64 object-cover"
                        />
                        <div className="p-6 flex-grow">
                          <h2 className="text-2xl font-semibold text-blue-700 mb-2">{product.name}</h2>
                          <p className="text-gray-600 mb-4">{product.description}</p>
                          <div className="items-center mb-4">
                            <span className="text-xl font-bold text-blue-600">{VNDCurrencyFormat.format(product.price)}</span>
                            <div className="flex justify-between items-center mb-6">
                              <button
                                onClick={() => handleProductClick(product.id)}
                                className="bg-blue-500 text-white px-4 py-2 rounded-full flex items-center justify-center w-full transition-colors duration-300 hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50"
                                aria-label={`View details for ${product.name}`}
                              >
                                <FiEye className="mr-2" />
                                Xem chi tiết
                              </button>
                              <div className="mx-2" />
                              <button
                                onClick={() => handleAddToCart(product.id, quantity)}
                                className="bg-green-500 text-white px-4 py-2 rounded-full flex items-center justify-center w-full transition-colors duration-300 hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50"
                                aria-label={`Add ${product.name} to cart`}
                              >
                                <FiShoppingCart className="mr-2" />
                                Thêm vào giỏ
                              </button>
                            </div>
                          </div>
                        </div>
                      </div>
                    ))}
                  </div>
                ) : (
                  <div className="flex flex-col items-center justify-center py-10">
                    <FiFrown className="text-5xl text-gray-400 mb-4" />
                    <h2 className="text-xl font-bold text-gray-600">Không có kết quả cho sản phẩm</h2>
                  </div>
                )}
              </>
            ) : (
              <>
                <h2 className="text-3xl font-bold text-gray-800 mb-6 mt-6">Sản phẩm nổi bật</h2>
                <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
                  {products.length > 0 ? (
                    products.map((product) => (
                      <div
                        key={product.id}
                        className="bg-white rounded-lg shadow-lg overflow-hidden transition-transform duration-300 hover:scale-105"
                      >
                        <img
                          src={product.imageUrls[0]}
                          alt={product.name}
                          className="w-full h-64 object-cover"
                        />
                        <div className="p-6 flex-grow">
                          <h2 className="text-2xl font-semibold text-blue-700 mb-2">{product.name}</h2>
                          <p className="text-gray-600 mb-4">{product.description}</p>
                          <div className="items-center mb-4">
                            <span className="text-xl font-bold text-blue-600">{VNDCurrencyFormat.format(product.price)}</span>
                            <div className="flex justify-between items-center mb-6">
                              <button
                                onClick={() => handleProductClick(product.id)}
                                className="bg-blue-500 text-white px-4 py-2 rounded-full flex items-center justify-center w-full transition-colors duration-300 hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50"
                                aria-label={`View details for ${product.name}`}
                              >
                                <FiEye className="mr-2" />
                                Xem chi tiết
                              </button>
                              <div className="mx-2" />
                              <button
                                onClick={() => handleAddToCart(product.id, quantity)}
                                className="bg-green-500 text-white px-4 py-2 rounded-full flex items-center justify-center w-full transition-colors duration-300 hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50"
                                aria-label={`Add ${product.name} to cart`}
                              >
                                <FiShoppingCart className="mr-2" />
                                Thêm vào giỏ
                              </button>
                            </div>
                          </div>
                        </div>
                      </div>
                    ))
                  ) : (
                    <div className="flex flex-col items-center justify-center py-10">
                      <FiFrown className="text-5xl text-gray-400 mb-4" />
                      <h2 className="text-xl font-bold text-gray-600">Không có sản phẩm nào!</h2>
                    </div>
                  )}
                </div>
              </>
            )}
          </>
        )}
        <h2 className="text-3xl font-bold text-gray-800 mb-6 mt-6">Đã xem gần đây</h2>
        <div className="recently-viewed grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">

          {recentlyViewed.map(product => (
            <div
              key={product.productId}
              className="bg-white rounded-lg shadow-lg overflow-hidden transition-transform duration-300 hover:scale-105"
            >
              <img
                src={product.imageUrls[0]}
                alt={product.productName}
                className="w-full h-64 object-cover"
              />
              <div className="p-6 flex-grow">
                <h2 className="text-2xl font-semibold text-blue-700 mb-2">{product.productName}</h2>
                <p className="text-gray-600 mb-4">{product.productDescription}</p>
                <div className="items-center mb-4">
                  <span className="text-xl font-bold text-blue-600">{VNDCurrencyFormat.format(product.productPrice)}</span>
                  <div className="flex justify-between items-center mb-6">
                    <button
                      onClick={() => handleProductClick(product.productId)}
                      className="bg-blue-500 text-white px-4 py-2 rounded-full flex items-center justify-center w-full transition-colors duration-300 hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50"
                      aria-label={`View details for ${product.productName}`}
                    >
                      <FiEye className="mr-2" />
                      Xem chi tiết
                    </button>
                    <div className="mx-2" />
                    <button
                      onClick={() => handleAddToCart(product.id, quantity)}
                      className="bg-green-500 text-white px-4 py-2 rounded-full flex items-center justify-center w-full transition-colors duration-300 hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50"
                      aria-label={`Add ${product.productName} to cart`}
                    >
                      <FiShoppingCart className="mr-2" />
                      Thêm vào giỏ
                    </button>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );


};

export default Home;
