---
inclusion: auto
rai_version: "1.1.1"
---

# RAI — Responsible AI Integration Rules

> **VERSION CHECK (bắt buộc mỗi session):**
> Gọi `get_rai_config(version="1.1.1")` ở đầu session.
> Nếu response trả `status: "update_available"` → cập nhật file này bằng content mới từ server.

> **MCP SERVER:** Tools trong file này thuộc MCP server AI-DLC (AI Context Engine).
> Server có thể được đặt tên khác nhau: `RAI`, `ai-context-engine-company`, `ci-rai`, v.v.
> Không quan trọng tên — chỉ cần server cung cấp các tool dưới đây là đúng.

## 1. Core Rule: Dùng execute_flow cho mọi task có ticket

Khi user yêu cầu implement/fix/deploy/spec/hotfix có ticket:
BẮT BUỘC gọi `execute_flow`. KHÔNG tự implement bên ngoài flow.

## 2. Flow Selection

- `b3_shape`: viết requirements, design, publish Confluence
- `b5_build`: code feature, fix bug
- `b6_verify`: chạy test, quality check
- `b7_ship`: deploy UAT/PROD
- `c1_ams`: incident, hotfix
- `b0_planning`: sprint planning

## 3. Workflow

1. `classify_intent` (auto-detect flow + issue_key)
2. `execute_flow` (drive end-to-end)
3. Tuân theo từng phase, KHÔNG skip
4. Dừng tại HITL checkpoint, đợi user confirm

## 4. Khi KHÔNG cần execute_flow

- Hỏi thông tin: dùng `jira_get_issue`
- Tìm context: dùng `search_context_hybrid`
- Giải thích code, trả lời câu hỏi

## 5. Context (bắt buộc trước khi code)

1. `get_minimal_context` — overview nhanh
2. `search_context_hybrid` — tìm code/docs
3. `get_work_context` — aggregate Jira+Confluence+Git

## 6. Quality Gates (bắt buộc trước deploy)

- `qge_pre_ci_gate`: sau commit
- `qge_analyze`: sau viết code
- `qge_pre_uat_gate`: trước UAT
- `qge_pre_prod_gate`: trước PROD

## 7. Conventions

- **Branch**: `features/{dev}-{ticket}-{description}` (e.g. `features/datnm11-FV-1234-add-extrainfo`)
- **Commit**: conventional commit format (dùng `cgp_generate_commit_message` nếu cần)
- **MR**: link Jira ticket (dùng `create_merge_request` — auto-link)
- **Chain**: luôn resolve `chain_id` từ Jira project hoặc repo URL — KHÔNG đoán

## 8. HITL Checkpoints

Khi flow có `checkpoint.blocking: true` → DỪNG, hiển thị message cho user, đợi confirm.
KHÔNG tự bypass checkpoint. KHÔNG chọn thay user. User quyết định `confirm` hoặc `revise`.

## 9. Tool Routing Quick Reference

| Cần làm gì | Tool |
|------------|------|
| Overview repo nhanh | `get_minimal_context` |
| Tìm code/docs | `search_context_hybrid` |
| Đọc Jira ticket | `jira_get_issue` |
| Tạo Jira ticket | `jira_create_from_intent` |
| Tạo branch + commit + MR | `create_feature_branch` → `commit_changes` → `create_merge_request` |
| Xem repo structure | `get_repo_structure` |
| Trigger CI build | `trigger_ci_pipeline` |
| Deploy UAT/PROD | `promote_to_uat` / `promote_to_prod` |
| Incident handling | `incident_triage` → `incident_diagnose` → `start_hotfix` |
| Publish to Confluence | `publish_design_to_confluence` / `publish_requirements_to_confluence` |

## 10. Anti-patterns (KHÔNG làm)

- ❌ Code trực tiếp mà không qua `execute_flow`
- ❌ Skip quality gate trước deploy
- ❌ Commit mà không link Jira ticket
- ❌ Deploy mà không có HITL approval
- ❌ Implement mà chưa lấy context
- ❌ Tự đoán chain_id — luôn resolve từ ticket/repo
- ❌ Bypass checkpoint blocking

## 11. Hiển thị Options — User chọn, không gõ

Khi tool response trả về danh sách options (checkpoint, flow choices, confirm/revise...):

**BẮT BUỘC** hiển thị dạng Kiro interactive choices:

```
[1]: Chọn hành động tiếp theo:
a. **Confirm** — tiếp tục flow
b. **Revise** — quay lại sửa phase trước
c. **Skip** — bỏ qua bước này

(Reply: "1=a" hoặc gõ tự do)
```

### Rules hiển thị:

- `execute_flow` trả `checkpoint.options` → format thành `[N]: question` + `a/b/c. **Option**`
- `classify_intent` trả nhiều flow candidates → mỗi flow = 1 option (a, b, c...)
- `needs_clarification` → hiển thị câu hỏi + options nếu có
- Cho phép user reply `1=a` hoặc gõ text tự do
- **KHÔNG** tự chọn thay user tại HITL checkpoint
- Sau user chọn → map option về action → gọi lại `execute_flow(action="confirm")` tương ứng

Luôn kết thúc bằng: "Reply tên option để tiếp tục."
KHÔNG tự chọn thay user. KHÔNG tiếp tục khi chưa có reply.
