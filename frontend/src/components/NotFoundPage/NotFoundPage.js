import React from 'react';

const NotFoundPage = () => {
  return (
    <div className='g-min-height-90vh g-flex-centered g-pa-15'>
      <div className='text-center g-flex-centered-item g-position-rel g-pb-15'>
        <div className='g-font-size-180 g-font-size-240--sm g-line-height-1 g-font-weight-600er g-color-gray-light-v4'>404</div>
        <div className='g-absolute-centered'>
          <h1 className='g-color-black g-mt-minus-8 mb-0'>404&nbsp;Not&nbsp;Found</h1>
          <hr className='g-brd-gray-light-v3 g-my-15' />
        </div>
      </div>
    </div>
  );
};

export default NotFoundPage;
