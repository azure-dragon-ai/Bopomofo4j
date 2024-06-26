```shell
brew install git-lfs
git lfs install
git lfs track "words.txt"
git lfs track "docs/words.txt"
git add .gitattributes

git filter-branch --tree-filter 'rm -rf docs/words.txt' HEAD
```