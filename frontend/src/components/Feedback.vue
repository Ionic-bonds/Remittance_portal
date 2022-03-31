<!-- 
ElMessage paramters: 
type: Success, Warning, Message and Error
dangerouslyUseHTMLString: true for HTML string
duration : 3000ms (default)
 -->

<script >
// import { ElNotification } from 'element-plus'
import { ElMessage, ElMessageBox, ElNotification, ElLoading } from 'element-plus'

const Feedback = {
  open1: (msg, type) => {
    ElMessage({
      showClose: true,
      message: msg,
      type: type,
      center: true,
      grouping: true,
      duration: 5000
    })
  },
  open2: async () => {
    var result = ElMessageBox.confirm(
    'Click OK to return to the previous step',
    'Warning',
    {
      confirmButtonText: 'OK',
      cancelButtonText: 'Cancel',
      type: 'warning',
    }
    )
    .then(() => {
      ElMessage({
        type: 'info',
        message: 'Please upload file again!',
      })
      return true
    })
    .catch(() => {
      // ElMessage({
      //   type: 'info',
      //   message: 'Delete canceled',
      // })
      return false
    })
    return result
  },
  open6: async () => {
    var result = ElMessageBox.confirm(
    'Click OK to validate and upload to API sandbox',
    'Finish Mapping?',
    {
      confirmButtonText: 'OK',
      cancelButtonText: 'Cancel',
      type: 'warning',
    }
    )
    .then(() => {
      return true
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: 'Please continue mapping',
      })
      return false
    })
    return result
  },

  open3: (msg, title) => {
    ElNotification.success({
      message: msg,
      title: title,
      duration: 5000,
      position: 'bottom-right',
    })
  },

  open5: (msg, title) => {
  ElNotification.error({
    message: msg,
    title: title,
    duration: 5000,
    position: 'bottom-right',
  })
  },

  open4: async () => {
    var result = ElMessageBox.prompt('Row number for Headers', 'Before uploading...', {
    confirmButtonText: 'OK',
    cancelButtonText: 'Cancel',
    inputType: "number",
    inputValue: 1,
    inputValidator: (val) => {return val > 0},
    inputErrorMessage: "Header row must be more than 0"
    })
    .then(({ value }) => {
      return value
    })
    .catch(() => {
      return false
    })

    return result
  }

}

export default Feedback

</script>
