class { 'postgresql::server':
  ip_mask_allow_all_users => '0.0.0.0/0',
}

postgresql::server::db {'babble':
  user     => 'babble',
  password => postgresql_password('babble','babble'),
}

