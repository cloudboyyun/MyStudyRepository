// 表单校验规则由 schema2code 生成，不建议直接修改校验规则，而建议通过 schema2code 生成, 详情: https://uniapp.dcloud.net.cn/uniCloud/schema


const validator = {
  "title": {
    "rules": [
      {
        "required": true,
        "errorMessage": "{label}不能为空"
      },
      {
        "format": "string"
      }
    ],
    "label": "标题"
  },
  "time": {
    "rules": [
      {
        "required": true,
        "errorMessage": "{label}不能为空"
      },
      {
        "format": "timestamp"
      }
    ],
    "label": "时间"
  },
  "channel": {
    "rules": [
      {
        "required": true,
        "errorMessage": "{label}不能为空"
      },
      {
        "format": "string"
      }
    ],
    "label": "频道"
  },
  "pic": {
    "rules": [
      {
        "format": "string"
      },
      {
        "format": "url",
        "errorMessage": "{label}格式无效"
      }
    ],
    "label": "图片"
  },
  "status": {
    "rules": [
      {
        "format": "array"
      },
      {
        "range": [
          {
            "value": 1,
            "text": "有效"
          },
          {
            "value": 0,
            "text": "无效"
          }
        ]
      }
    ],
    "label": "状态",
    "defaultValue": 1
  }
}

const enumConverter = {
  "status_valuetotext": [
    {
      "value": 1,
      "text": "有效"
    },
    {
      "value": 0,
      "text": "无效"
    }
  ]
}

function filterToWhere(filter, command) {
  let where = {}
  for (let field in filter) {
    let { type, value } = filter[field]
    switch (type) {
      case "search":
        if (typeof value === 'string' && value.length) {
          where[field] = new RegExp(value)
        }
        break;
      case "select":
        if (value.length) {
          let selectValue = []
          for (let s of value) {
            selectValue.push(command.eq(s))
          }
          where[field] = command.or(selectValue)
        }
        break;
      case "range":
        if (value.length) {
          let gt = value[0]
          let lt = value[1]
          where[field] = command.and([command.gte(gt), command.lte(lt)])
        }
        break;
      case "date":
        if (value.length) {
          let [s, e] = value
          let startDate = new Date(s)
          let endDate = new Date(e)
          where[field] = command.and([command.gte(startDate), command.lte(endDate)])
        }
        break;
      case "timestamp":
        if (value.length) {
          let [startDate, endDate] = value
          where[field] = command.and([command.gte(startDate), command.lte(endDate)])
        }
        break;
    }
  }
  return where
}

export { validator, enumConverter, filterToWhere }