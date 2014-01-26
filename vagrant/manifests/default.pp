class { 'postgresql::globals':
  version => '9.2',
  manage_package_repo => true
}-> 
class { 'postgresql::server':
}

postgresql::server::db {'babble':
  user     => 'babble',
  password => postgresql_password('babble','babble')
}

