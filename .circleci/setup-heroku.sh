#!/bin/bash

sudo snap install --classic heroku
sudo apt-get update

echo -e "machine api.heroku.com \n  login $HEROKU_LOGIN\n  password $HEROKU_API_KEY\nmachine git.heroku.com\n  login $HEROKU_LOGIN\n  password $HEROKU_API_KEY" >> ~/.netrc
 
heroku git:remote -a $HEROKU_APP