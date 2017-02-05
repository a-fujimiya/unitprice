# Unit Price
内容量(Net)と値段(Price)から単価(UnitPrice)を求める。

## 試したこと
1. [Butterknife v8.4.0](http://jakewharton.github.io/butterknife/)の導入
1. [Perceler v1.0.3](https://github.com/johncarl81/parceler)の導入
1. Parcelerと併用する場合に build.gradleに Butterknifeの annotationProcessorをどう書くか
1. PercentRelativeLayoutの使い方
1. EditTextをタップした時にキーボードを表示させない方法

## 駄目なところ
1. データを MainActivityが持っている
1. 更新する値を区別する方法(どの値が focusを持っているかを activityに渡している)
1. 更新する値を viewから拾っている
1. 更新した値を表示するのに、activityから fragmentの publicメソッドをコールしている
1. メンバ変数に頼り過ぎな気がする

## 分かっているけど無視したところ
1. 入力値の桁数チェック
1. 入力欄をダブルタップすると文字が選択状態になる
