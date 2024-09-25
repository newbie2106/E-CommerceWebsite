import React, { useEffect, useState } from 'react';

const ScrollToTopButton = () => {
  const [isVisible, setIsVisible] = useState(false);

  useEffect(() => {
    const handleScroll = () => {
      if (window.scrollY > 100) {
        setIsVisible(true);
      } else {
        setIsVisible(false);
      }
    };

    window.addEventListener('scroll', handleScroll);
    
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  const scrollToTop = () => {
    window.scrollTo({
      top: 0,
      behavior: 'smooth',
    });
  };

  return (
    <button
      onClick={scrollToTop}
      className={`fixed bottom-4 right-4 p-3 mb-12 bg-blue-600 text-white rounded-full transition-transform duration-300 ease-in-out ${isVisible ? 'opacity-100' : 'opacity-0 pointer-events-none'} hover:bg-blue-900`}
      style={{ zIndex: 1000 }}
    >
      &#8593; 
    </button>
  );
};

export default ScrollToTopButton;
