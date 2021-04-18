#!/bin/bash

curl https://cli-assets.heroku.com/install-ubuntu.sh | sh
sudo apt-get update
sudo apt-get install postgresql

cat > ~/.netrc << EOF
machine api.heroku.com
  login $HEROKU_LOGIN
  password $HEROKU_API_KEY
machine git.heroku.com
  login $HEROKU_LOGIN
  password $HEROKU_API_KEY
EOF

 
heroku git:remote -a $HEROKU_APP