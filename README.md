<!--
 * @Author: Rdj kaisanren@gmail.com
 * @Date: 2024-11-28 20:52:48
 * @LastEditors: Rdj kaisanren@gmail.com
 * @LastEditTime: 2024-12-19 19:37:42
 * @FilePath: \undefinedd:\Intellij IDEA Community   IJ\endingwork\README.md
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
基于servlet的JAVAweb项目
# version 1
version1 实现对微博的用户注册功能
# version 2
version2 实现微博用户的会话管理，即：发布话题和保存到本地的功能 以及实现了会话记录删除的功能
# version3
1. 通过配置`xml` 文件的
            <param-name></param-name>
            <param-value></param-value>
          标签设置用户数据文件夹USERS
2. 将涉及USERS的JAVA文件中的非重写方法全部归到UserService类中方便对业务进行修改，同时解决了USERS的多次设置问题
# version4
1. 实现对修改version2中的view目录下的*.view用jsp替代，使得页面具有实时更新的功能 新增blah类，实现对会话的新增删除和增加、查看的修改
2. 增加过滤器和监听器：
MemberFilter来实现请求过滤，确保只有登录用户才能访问特定资源，增强了应用的安全性。
通过实现BlogContextListener，我们在Web应用启动时初始化了UserService实例，并将其存储在ServletContext中，为整个应用提供了一个全局可访问的服务实例。
3. 1) 将register.html ---> register.jsp用于可以动态的显示注册失败的错误信息，就在register页面显示而不是重定向到error.jsp
2) index.html --> index.jsp 实现在用户登录时用户名或者密码错误时进行提示“用户名或密码错误”





