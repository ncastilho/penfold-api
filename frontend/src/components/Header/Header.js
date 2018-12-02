import React from 'react';
import { Link } from 'react-router-dom';

const Header = () => {
  return (
    <header id='js-header' className='u-header u-header--static'>
      <div className='u-header__section u-header__section--light g-bg-white g-transition-0_3 g-py-10' data-header-fix-moment-exclude='g-py-10' data-header-fix-moment-classes='u-shadow-v18 g-py-0'>
        <nav className='navbar navbar-expand-lg'>
          <div className='container'>
            <Link to='/' className='navbar-brand'>
              <img src='assets/img/logo/logo.png' alt='' />
            </Link>
          </div>
        </nav>
      </div>
    </header>
  );
};

export default Header;
