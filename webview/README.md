## 主要点
1. android调用js
2. js调用android
3. onShowFileChooser，把本地文件通过Uri的方式传给H5,通常是传图片显示再当前页面
4. 对传入的url重组，判断是否是https，参数重组防止参数重复等
5. 单独在一个进程，所以要考虑跨进程通信场景，应用初始化进程判断
