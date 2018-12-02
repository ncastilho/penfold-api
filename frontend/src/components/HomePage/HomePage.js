import React from 'react';

const HomePage = () => {
  const contactList =
    <div className="list-group list-group-border-0 g-mb-40">
      <div className="shortcode-html">
        <ul className="list-unstyled">
          <li className="g-brd-around g-brd-gray-light-v4 g-mb-minus-1">
            <div className="media">
              <div className="media-body">
                <figure className="u-block-hover u-shadow-v19 g-bg-white g-rounded-4 g-pa-15">
                  <div className="d-flex justify-content-start">
                    <div className="d-block">
                      <div className="g-mb-5">
                        <h4 className="h5 g-mb-0">John Doe</h4>
                      </div>
                      <em className="d-block g-color-gray-dark-v5 g-font-style-normal g-font-size-13 g-mb-2">john.doe@htmlstream.com</em>
                      <em class="d-block g-color-gray-dark-v5 g-font-style-normal g-font-size-12">(+44) 456 789059</em>
                    </div>
                  </div>
                </figure>
              </div>
            </div>
          </li>
          <li className="g-brd-around g-brd-gray-light-v4 g-mb-minus-1">
            <div className="media">
              <div className="media-body">
                <figure className="u-block-hover u-shadow-v19 g-bg-white g-rounded-4 g-pa-15">
                  <div className="d-flex justify-content-start">
                    <div className="d-block">
                      <div className="g-mb-5">
                        <h4 className="h5 g-mb-0">Jane Doe</h4>
                      </div>
                      <em className="d-block g-color-gray-dark-v5 g-font-style-normal g-font-size-13 g-mb-2">jane.doe@htmlstream.com</em>
                      <em className="d-block g-color-gray-dark-v5 g-font-style-normal g-font-size-12">(+44) 986 543768</em>
                    </div>
                  </div>
                </figure>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>;

  return (
    <section className="g-min-height-80vh">
      <div className="container">
        <div className="row">
          <div className="col-lg-3 g-mb-50 g-mb-0--lg">
              { contactList }
          </div>
          <div className="col-lg-9">
            <ul className="nav nav-justified u-nav-v1-1 u-nav-primary g-brd-bottom--md g-brd-bottom-2 g-brd-primary g-mb-20"
                role="tablist" data-target="nav-1-1-default-hor-left-underline" data-tabs-mobile-type="slide-up-down"
                data-btn-classes="btn btn-md btn-block rounded-0 u-btn-outline-primary g-mb-20">
              <li className="nav-item">
                <a className="nav-link g-py-10 active" data-toggle="tab" href="#nav-1-1-default-hor-left-underline--1"
                   role="tab">Contact Details</a>
              </li>
              <li className="nav-item">
                <a className="nav-link g-py-10" data-toggle="tab" href="#nav-1-1-default-hor-left-underline--2"
                   role="tab">Messages</a>
              </li>
              <li className="nav-item">
                <a className="nav-link g-py-10" data-toggle="tab" href="#nav-1-1-default-hor-left-underline--3"
                   role="tab">History</a>
              </li>
              <li className="nav-item">
                <a className="nav-link g-py-10" data-toggle="tab" href="#nav-1-1-default-hor-left-underline--4"
                   role="tab">Notification Settings</a>
              </li>
            </ul>

            <div id="nav-1-1-default-hor-left-underline" className="tab-content">

              <div className="tab-pane fade show active" id="nav-1-1-default-hor-left-underline--1" role="tabpanel"
                   data-parent="#nav-1-1-default-hor-left-underline">
                <h2 className="h4 g-font-weight-300">Manage Name, Email Address and Phone Number</h2>
                <p>Below are name, email and mobile contacts on file for this contact.</p>

                <ul className="list-unstyled g-mb-30">

                  <li className="d-flex align-items-center justify-content-between g-brd-bottom g-brd-gray-light-v4 g-py-15">
                    <div className="g-pr-10">
                      <strong
                          className="d-block d-md-inline-block g-color-gray-dark-v2 g-width-200 g-pr-10">Name</strong>
                      <span className="align-top">John Doe</span>
                    </div>
                    <span>
                        <i className="icon-pencil g-color-gray-dark-v5 g-color-primary--hover g-cursor-pointer g-pos-rel g-top-1"></i>
                      </span>
                  </li>

                  <li className="d-flex align-items-center justify-content-between g-brd-bottom g-brd-gray-light-v4 g-py-15">
                    <div className="g-pr-10">
                      <strong className="d-block d-md-inline-block g-color-gray-dark-v2 g-width-200 g-pr-10">Email address</strong>
                      <span className="align-top">john.doe@htmlstream.com</span>
                    </div>
                    <span>
                        <i className="icon-pencil g-color-gray-dark-v5 g-color-primary--hover g-cursor-pointer g-pos-rel g-top-1"></i>
                      </span>
                  </li>

                  <li className="d-flex align-items-center justify-content-between g-brd-bottom g-brd-gray-light-v4 g-py-15">
                    <div className="g-pr-10">
                      <strong className="d-block d-md-inline-block g-color-gray-dark-v2 g-width-200 g-pr-10">Mobile
                        number</strong>
                      <span className="align-top">(+44) 456 789059</span>
                    </div>
                    <span>
                        <i className="icon-pencil g-color-gray-dark-v5 g-color-primary--hover g-cursor-pointer g-pos-rel g-top-1"></i>
                      </span>
                  </li>
                </ul>

                <div className="text-sm-right">
                  <a className="btn u-btn-darkgray rounded-0 g-py-12 g-px-25 g-mr-10" href="#!">Cancel</a>
                  <a className="btn u-btn-primary rounded-0 g-py-12 g-px-25" href="#!">Save Changes</a>
                </div>
              </div>

              <div className="tab-pane fade" id="nav-1-1-default-hor-left-underline--2" role="tabpanel"
                   data-parent="#nav-1-1-default-hor-left-underline">
                <h2 className="h4 g-font-weight-300">Manage Messages</h2>
                <p className="g-mb-25">Add messages, change send time and so on.</p>

                <form>

                  <hr className="g-brd-gray-light-v4 g-my-20" />

                  <div className="text-sm-right">
                    <a className="btn u-btn-darkgray rounded-0 g-py-12 g-px-25 g-mr-10" href="#!">Cancel</a>
                    <a className="btn u-btn-primary rounded-0 g-py-12 g-px-25" href="#!">Save Changes</a>
                  </div>

                </form>
              </div>

              <div className="tab-pane fade" id="nav-1-1-default-hor-left-underline--3" role="tabpanel"
                   data-parent="#nav-1-1-default-hor-left-underline">
                <h2 className="h4 g-font-weight-300">Message History</h2>
                <p className="g-mb-25">Below shows the history and send / received status for triggered messages.</p>

                <form>

                </form>
              </div>

              <div className="tab-pane fade" id="nav-1-1-default-hor-left-underline--4" role="tabpanel"
                   data-parent="#nav-1-1-default-hor-left-underline">
                <h2 className="h4 g-font-weight-300">Manage Notifications</h2>
                <p className="g-mb-25">Below are the notifications settings for this contact.</p>

                <form>

                  <div className="form-group">
                    <label className="d-flex align-items-center justify-content-between">
                      <span>SMS notification</span>
                      <div className="u-check">
                        <input className="g-hidden-xs-up g-pos-abs g-top-0 g-right-0" name="smsNotification"
                               type="checkbox" checked />
                          <div className="u-check-icon-radio-v7">
                            <i className="d-inline-block"></i>
                          </div>
                      </div>
                    </label>
                  </div>

                  <hr className="g-brd-gray-light-v4 g-my-20" />

                  <div className="text-sm-right">
                    <a className="btn u-btn-darkgray rounded-0 g-py-12 g-px-25 g-mr-10" href="#!">Cancel</a>
                    <a className="btn u-btn-primary rounded-0 g-py-12 g-px-25" href="#!">Save Changes</a>
                  </div>

                </form>
              </div>

            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default HomePage;
