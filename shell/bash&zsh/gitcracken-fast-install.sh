#!/bin/zsh

if (( ! $+commands[git] )) {
	print -P "%B%F{red}@= ERROR%b 缺失 Git%f"
	exit
}

if (( ! $+commands[yarn] )) {
	print -P "%B%F{red}@= ERROR%b 缺失 node/yarn%f"
	exit
}

print -P "B%F{blue}@= 检查更新中%b%f"
if [[ -d ./GitCracken ]] {
	cd GitCracken
	git checkout -- .
	git pull
} else {
	print -P "\n%F{blue}    初始化GitCracken储存库%b"
	git clone https://github.com/5cr1pt/GitCracken.git
	cd GitCracken
}
cd GitCracken

print -P "\n%B%F{blue}@= 正在构建本地应用%b%f"
yarn install
yarn build

print -P "\n%B%F{blue}@= 正在进行自动破解打包%b%f"
sudo node dist/bin/gitcracken.js patcher