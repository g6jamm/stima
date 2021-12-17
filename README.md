# stima

![GitHub repo size](https://img.shields.io/github/repo-size/g6jamm/stima)
![GitHub Issues](https://img.shields.io/github/issues/g6jamm/stima)
![GitHub contributors](https://img.shields.io/github/contributors/g6jamm/stima)
![GitHub stars](https://img.shields.io/github/stars/g6jamm/stima)
![GitHub forks](https://img.shields.io/github/forks/g6jamm/stima)
![GitHub licence](https://img.shields.io/github/license/g6jamm/stima.svg)

Stima is a project estimation tool.

## Using

To use stima, follow this link: https://stima2.herokuapp.com/

Demo user:<br>
`user@demo.com` : `Demo`
<br>
Demo admin user:<br>
`admin@demo.com` : `Demo`

## Contributing to stima

To contribute to stima, follow these steps:

1. Fork this repository.
2. Create a branch: `git checkout -b <branch_name>`.
3. Make your changes and commit them: `git commit -m '<commit_message>'`
4. Push to the original branch: `git push origin <project_name>/<location>`
5. Create the pull request.

Alternatively see the GitHub documentation
on [creating a pull request](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request)
.

## Run on localhost

### Step 1 - download:

```
git clone git@github.com:g6jamm/stima.git && cd stima
```

### Step 2 - install:

```
bash install.sh
```

### Step 3 - build:

```
mvn install
```

### Step 4 - run:

```
mvn spring-boot:run
```

## Contributors

Thanks to the following people who have contributed to this project:

* [@Andreassim](https://github.com/Andreassim)
* [@Jarkyman](https://github.com/Jarkyman)
* [@MathiasReker](https://github.com/MathiasReker)
* [@Moshizzl3](https://github.com/Moshizzl3)

## Deployment to Heruko

How to deploy the on Heroku

1. Create an account on Heruko
2. Click on "new" in the right corner
3. Select new app in the drop-down menu
4. Name the app and select region (Europe recommended)
5. During deploy method, select GitHub (this requires you to link your GitHub to Heroku)
6. Search for your repository under “Connect to GitHub” and press connect
7. Select “Enable Automatic Deploys” if you want to redeploy every time the production branch changes.
8. Select which branch to deploy and press deploy branch

## License

This project uses the following license: [MIT License](https://github.com/g6jamm/stima/blob/develop/LICENSE).
