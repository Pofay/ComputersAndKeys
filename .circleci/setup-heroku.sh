#!/bin/bash

curl https://cli-assets.heroku.com/install-ubuntu.sh | sh
sudo apt-get update

sudo echo -e "machine api.heroku.com \n  login $HEROKU_LOGIN\n  password $HEROKU_API_KEY\nmachine git.heroku.com\n  login $HEROKU_LOGIN\n  password $HEROKU_API_KEY" >> ~/.netrc
 
heroku git:remote -a $HEROKU_APP