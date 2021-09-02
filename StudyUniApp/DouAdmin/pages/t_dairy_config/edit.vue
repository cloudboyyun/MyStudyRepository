<template>
  <view class="uni-container">
    <uni-forms ref="form" :value="formData" validateTrigger="bind">
      <uni-group>
        <uni-forms-item name="dairy_data_version" label="日历数据版本号" required>
          <uni-easyinput placeholder="日历数据版本号" v-model="formData.dairy_data_version"></uni-easyinput>
        </uni-forms-item>
      </uni-group>
      <uni-group>
        <uni-forms-item name="dairy_data_max_year" label="最大年" required>
          <uni-easyinput placeholder="日历数据中干净数据的最大年份" type="number" v-model="formData.dairy_data_max_year"></uni-easyinput>
        </uni-forms-item>
        <uni-forms-item name="dairy_data_max_month" label="最大月" required>
          <uni-data-checkbox v-model="formData.dairy_data_max_month" :localdata="formOptions.dairy_data_max_month_localdata"></uni-data-checkbox>
        </uni-forms-item>
      </uni-group>
      <uni-group>
        <uni-forms-item name="dairy_data_min_year" label="最小年" required>
          <uni-easyinput placeholder="日历数据中干净数据的最小年份" type="number" v-model="formData.dairy_data_min_year"></uni-easyinput>
        </uni-forms-item>
        <uni-forms-item name="dairy_data_min_month" label="最小月" required>
          <uni-data-checkbox v-model="formData.dairy_data_min_month" :localdata="formOptions.dairy_data_min_month_localdata"></uni-data-checkbox>
        </uni-forms-item>
      </uni-group>
      <view class="uni-button-group">
        <button type="primary" class="uni-button" style="width: 100px;" @click="submit">提交</button>
        <navigator open-type="navigateBack" style="margin-left: 15px;">
            <button class="uni-button" style="width: 100px;">返回</button>
        </navigator>
      </view>
    </uni-forms>
  </view>
</template>

<script>
  import { validator } from '../../js_sdk/validator/t_dairy_config.js';

  const db = uniCloud.database();
  const dbCmd = db.command;
  const dbCollectionName = 't_dairy_config';

  function getValidator(fields) {
    let result = {}
    for (let key in validator) {
      if (fields.includes(key)) {
        result[key] = validator[key]
      }
    }
    return result
  }

  export default {
    data() {
      let formData = {
        "dairy_data_version": "",
        "dairy_data_max_year": null,
        "dairy_data_max_month": null,
        "dairy_data_min_year": null,
        "dairy_data_min_month": null
      }
      return {
        formData,
        formOptions: {
          "dairy_data_max_month_localdata": [
            {
              "value": 1,
              "text": 1
            },
            {
              "value": 2,
              "text": 2
            },
            {
              "value": 3,
              "text": 3
            },
            {
              "value": 4,
              "text": 4
            },
            {
              "value": 5,
              "text": 5
            },
            {
              "value": 6,
              "text": 6
            },
            {
              "value": 7,
              "text": 7
            },
            {
              "value": 8,
              "text": 8
            },
            {
              "value": 9,
              "text": 9
            },
            {
              "value": 10,
              "text": 10
            },
            {
              "value": 11,
              "text": 11
            },
            {
              "value": 12,
              "text": 12
            }
          ],
          "dairy_data_min_month_localdata": [
            {
              "value": 1,
              "text": 1
            },
            {
              "value": 2,
              "text": 2
            },
            {
              "value": 3,
              "text": 3
            },
            {
              "value": 4,
              "text": 4
            },
            {
              "value": 5,
              "text": 5
            },
            {
              "value": 6,
              "text": 6
            },
            {
              "value": 7,
              "text": 7
            },
            {
              "value": 8,
              "text": 8
            },
            {
              "value": 9,
              "text": 9
            },
            {
              "value": 10,
              "text": 10
            },
            {
              "value": 11,
              "text": 11
            },
            {
              "value": 12,
              "text": 12
            }
          ]
        },
        rules: {
          ...getValidator(Object.keys(formData))
        }
      }
    },
    onLoad(e) {
      if (e.id) {
        const id = e.id
        this.formDataId = id
        this.getDetail(id)
      }
    },
    onReady() {
      this.$refs.form.setRules(this.rules)
    },
    methods: {
      /**
       * 触发表单提交
       */
      submit() {
        uni.showLoading({
          mask: true
        })
        this.$refs.form.validate().then((res) => {
          this.submitForm(res)
        }).catch(() => {
          uni.hideLoading()
        })
      },

      submitForm(value) {
        // 使用 clientDB 提交数据
        db.collection(dbCollectionName).doc(this.formDataId).update(value).then((res) => {
          uni.showToast({
            title: '修改成功'
          })
          this.getOpenerEventChannel().emit('refreshData')
          setTimeout(() => uni.navigateBack(), 500)
        }).catch((err) => {
          uni.showModal({
            content: err.message || '请求服务失败',
            showCancel: false
          })
        }).finally(() => {
          uni.hideLoading()
        })
      },

      /**
       * 获取表单数据
       * @param {Object} id
       */
      getDetail(id) {
        uni.showLoading({
          mask: true
        })
        db.collection(dbCollectionName).doc(id).field("dairy_data_version,dairy_data_max_year,dairy_data_max_month,dairy_data_min_year,dairy_data_min_month").get().then((res) => {
          const data = res.result.data[0]
          if (data) {
            this.formData = data
          }
        }).catch((err) => {
          uni.showModal({
            content: err.message || '请求服务失败',
            showCancel: false
          })
        }).finally(() => {
          uni.hideLoading()
        })
      }
    }
  }
</script>
