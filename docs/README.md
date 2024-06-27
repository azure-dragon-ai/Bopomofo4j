```shell
brew install git-lfs
git lfs install
git lfs track "words.txt"
git lfs track "docs/words.txt"
git add .gitattributes

git filter-branch --tree-filter 'rm -rf docs/words.txt' HEAD
git filter-branch --tree-filter 'rm -rf docs/青宝&哪里都不去.txt' HEAD
git filter-branch --tree-filter 'rm -rf docs/words1.txt' HEAD
git filter-branch --tree-filter 'rm -rf docs/反对大熊猫&出国.txt' HEAD
```