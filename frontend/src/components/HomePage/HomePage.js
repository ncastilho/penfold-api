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
                      <em className="d-block g-color-gray-dark-v5 g-font-style-normal g-font-size-12">(+44) 456 789059</em>
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
          <div className="col-lg-12 g-mb-50 g-mb-0--lg">

            <a href="#!" className="btn btn-lg u-btn-blue rounded-0 g-mr-10 g-mb-15">Add Patient</a>
          </div>
        </div>
        <div className="row">
          <div className="col-lg-3 g-mb-50 g-mb-0--lg">
            <form>
              <div className="form-group g-mb-20">
                <input id="inputGroup1_1" className="form-control form-control-md rounded-0" type="text"  placeholder="Search..." />
              </div>
            </form>
              { contactList }
          </div>
          <div className="col-lg-9">
            <ul className="nav nav-justified u-nav-v1-1 u-nav-primary g-brd-bottom--md g-brd-bottom-2 g-brd-primary g-mb-20"
                role="tablist" data-target="nav-1-1-default-hor-left-underline" data-tabs-mobile-type="slide-up-down"
                data-btn-classes="btn btn-md btn-block rounded-0 u-btn-outline-primary g-mb-20">
              <li className="nav-item">
                <a className="nav-link g-py-10 active" data-toggle="tab" href="#nav-1-1-default-hor-left-underline--1"
                   role="tab">Patient Details</a>
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

              </div>

              <div className="tab-pane fade" id="nav-1-1-default-hor-left-underline--2" role="tabpanel"
                   data-parent="#nav-1-1-default-hor-left-underline">
                <h2 className="h4 g-font-weight-300">Manage Messages</h2>
                <p className="g-mb-5">Add messages, change send time and so on.</p>

                <form>

                  <div className="row">
                    <div className="col-lg-12 g-mb-50 g-mb-0--lg">
                      <div className="form-group g-mb-20">
                        <small className="form-text g-font-size-default g-mt-10 text-right">160 characters remaining</small>
                        <div className="input-group g-brd-primary--focus">
                          <textarea className="form-control form-control-md g-resize-none rounded-0" rows="4"
                                    placeholder="Ipsum Aenean Porta"></textarea>
                          <div className="input-group-append">
                            <span className="input-group-text rounded-0 g-bg-white g-color-gray-light-v1"><i
                                className="fa fa-pencil"></i></span>
                          </div>
                        </div>
                        <small className="form-text g-font-size-default g-mt-10"><i className="icon-clock g-mr-5"></i> <a href="javascript:void(0)" >Schedule</a> | <a href="javascript:void(0)" >Add</a></small>
                      </div>
                      <div className="form-group g-mb-20">
                        <div className="input-group g-brd-primary--focus">
                          <textarea className="form-control form-control-md g-resize-none rounded-0" rows="4"
                                    placeholder="Ipsum Aenean Porta">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus animi aperiam, aut et excepturi facere iusto libero maxime molestiae nam numquam ratione similique vel, vero, voluptates. Laudantium nam possimus ullam.</textarea>
                          <div className="input-group-append">
                            <span className="input-group-text rounded-0 g-bg-white g-color-gray-light-v1"><i
                                className="fa fa-pencil"></i></span>
                          </div>
                        </div>
                        <small className="form-text g-font-size-default g-mt-10"><i className="icon-clock g-mr-5"></i> <a href="javascript:void(0)" >Scheduled for 11:30</a> | <a href="javascript:void(0)" >Remove</a></small>
                      </div>
                      <div className="form-group g-mb-20">
                        <div className="input-group g-brd-primary--focus">
                          <textarea className="form-control form-control-md g-resize-none rounded-0" rows="4"
                                    placeholder="Ipsum Aenean Porta">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus animi aperiam, aut et excepturi facere iusto libero maxime molestiae nam numquam ratione similique vel, vero, voluptates. Laudantium nam possimus ullam.</textarea>
                          <div className="input-group-append">
                            <span className="input-group-text rounded-0 g-bg-white g-color-gray-light-v1"><i
                                className="fa fa-pencil"></i></span>
                          </div>
                        </div>
                        <small className="form-text g-font-size-default g-mt-10"><i className="icon-clock g-mr-5"></i> <a href="javascript:void(0)" >Scheduled for 14:00 PM</a> | <a href="javascript:void(0)" >Remove</a></small>
                      </div>
                    </div>
                  </div>

                </form>
              </div>

              <div className="tab-pane fade" id="nav-1-1-default-hor-left-underline--3" role="tabpanel"
                   data-parent="#nav-1-1-default-hor-left-underline">
                <h2 className="h4 g-font-weight-300">Message History</h2>
                <p className="g-mb-25">Below shows the history of triggered messages and their status.</p>

                <div className="table-responsive">
                  <table className="table table-bordered u-table--v2">
                    <thead className="text-uppercase g-letter-spacing-1">
                    <tr>
                      <th className="g-font-weight-300 g-color-black g-min-width-200">Message</th>
                      <th className="g-font-weight-300 g-color-black">Status</th>
                      <th className="g-font-weight-300 g-color-black">Date</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr>
                      <td className="align-middle">
                        <span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus asperiores aut consequatur debitis dignissimos eos ex facilis fugiat iure nemo numquam officia pariatur, quisquam saepe sunt vel veniam vitae? Ipsum.</span>
                      </td>
                      <td className="align-middle">
                        <a className="btn btn-block u-btn-primary g-rounded-50 g-py-5" href="#!">
                          <i className="fa fa-check g-mr-5"></i> Received
                        </a>
                      </td>
                      <td className="align-middle text-nowrap">
                        <span className="d-block g-mb-5"><i className="icon-calendar g-mr-5"></i> July 16, 2017</span>
                        <span className="d-block g-mb-5"><i className="icon-clock g-mr-5"></i> 11:30</span>
                      </td>
                    </tr>

                    <tr>
                      <td className="align-middle">
                        <span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Assumenda beatae cupiditate eum fuga fugiat itaque nihil provident quam quidem voluptate. Accusantium at culpa in quod? Enim fugiat iste quia veritatis.</span>
                      </td>

                      <td className="align-middle">
                        <a className="btn btn-block u-btn-red g-rounded-50 g-py-5" href="#!">
                          <i className="fa fa-times g-mr-5"></i> Failed
                        </a>
                      </td>
                      <td className="align-middle text-nowrap">
                        <span className="d-block g-mb-5"><i className="icon-calendar g-mr-5"></i> July 16, 2017</span>
                        <span className="d-block g-mb-5"><i className="icon-clock g-mr-5"></i> 14:00</span>
                      </td>
                    </tr>

                    <tr>
                      <td className="align-middle">
                        <span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci dolorum eius ipsam iste, molestias obcaecati perspiciatis quae soluta tenetur unde ut voluptatem! Aliquam eligendi error est fuga hic odit, unde!</span>
                      </td>

                      <td className="align-middle">
                        <a className="btn btn-block u-btn-orange g-rounded-50 g-py-5" href="#!">
                          <i className="fa fa-info g-mr-5"></i> Sent
                        </a>
                      </td>
                      <td className="align-middle text-nowrap">
                        <span className="d-block g-mb-5"><i className="icon-calendar g-mr-5"></i> July 17, 2017</span>
                        <span className="d-block g-mb-5"><i className="icon-clock g-mr-5"></i> 11:30</span>
                      </td>
                    </tr>

                    </tbody>
                  </table>
                </div>
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
                               type="checkbox" onChange={e => !e.target.checked} defaultChecked={true} />
                          <div className="u-check-icon-radio-v7">
                            <i className="d-inline-block"></i>
                          </div>
                      </div>
                    </label>
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
