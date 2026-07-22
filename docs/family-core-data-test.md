# Family Core — Data Test

> Dữ liệu test cho module Family (Long Châu + Vaccine). Cập nhật khi có data mới.

---

## 📱 Số điện thoại test

### Gia đình Long Châu (LC) — UAT

| # | SĐT | Ghi chú | CCCD |
|---|-----|---------|------|
| 1 | 0358597111 | GĐ LC 2 — sài được | CCCD Khang |
| 2 | 0945303971 | GĐ LC 2 — Trình dược viên (TDV) | CCCD 24 |
| 3 | 0923153712 | GĐ LC — sài được | |
| 4 | 0589158205 | GĐ LC | |
| 5 | 0589158206 | GĐ LC | |
| 6 | 0347204346 | GĐ LC | |
| 7 | 0932690296 | GĐ LC — đang có gia đình | |
| 8 | 0835089254 | **Không phải TDV** | |
| 9 | 0923153713 | | |

### Gia đình Vaccine (VAC) — CI

| # | SĐT | Ghi chú |
|---|-----|---------|
| 1 | 0932690296 | Đang có gia đình |
| 2 | 0907502443 | |
| 3 | 0983377835 | |
| 4 | 0963036567 | |
| 5 | 0984989127 | Đang test null giới tính/ngày sinh |

### Gia đình Vaccine (VAC) — UAT

| # | SĐT | Ghi chú |
|---|-----|---------|
| 1 | 0983377835 | |
| 2 | 0964802464 | |
| 3 | 0984989127 | Đang test null giới tính/ngày sinh |
| 4 | 0932690296 | Đang có gia đình |

---

## 🪪 CCCD Test

| # | SĐT | CCCD | Họ tên | Ghi chú |
|---|-----|------|--------|---------|
| 1 | 0932690296 | 079199025934 | | GĐ VAC |
| 2 | 0932690298 | 037163001204 | NGUYỄN TIẾN ĐẠT | |
| 3 | 0932690299 | 001092001434 | TRẦN VĂN SỬU | |
| 4 | 0932690255 | 037063001414 | | Không có ngày sinh, giới tính bị xóa. GĐ VAC đang gia đình LC |
| 5 | 0775505904 | 077550005904 | | GĐ 3 |

---

## 🛒 SKU Test — Tích điểm

| # | SKU | Tên SP | Barcode | Tích điểm | Ghi chú |
|---|-----|--------|---------|-----------|---------|
| 1 | 00001374 | — | 017870090016 | ❌ Không tích điểm | SKU ko tích điểm Fsell → testcase ko tích điểm |
| 2 | 00000462 | Acemuc | 009360590002 | ✅ Có tích điểm | |

---

## 👤 Phân loại SĐT

| SĐT | Là TDV? | Ghi chú |
|-----|---------|---------|
| 0945303971 | ✅ Có | Trình dược viên |
| 0923153712 | ❓ | |
| 0835089254 | ❌ Không | Không phải TDV |

---

## 🐛 Known Issues / Đang Fix

| # | Vấn đề | SĐT liên quan | Người fix | Status |
|---|--------|---------------|-----------|--------|
| 1 | Null giới tính + ngày sinh | 0932690296, 0984989127 | Thiên | Đang fix |
| 2 | GĐ VAC bị xóa, đang gia đình LC | 0932690255 | | |

---

## 📝 Ghi chú

- Số nào có giao dịch rồi → nhờ **anh Hùng clear data** để tạo giao dịch lại
- SĐT TDV: dùng để test case liên quan trình dược viên
- CCCD: dùng cho test OCR tạo gia đình

---

*Last Updated: 14/05/2026*


data test LÊ DUY KHANG 
Male
Phone
******9254/ 0835089254
Card
********5003
HÀ ANH TUẤNMale
Phone
******5401 0775465401
Card
********5001
DX TESTMale
Phone
******6894 
 Chờ giá lên: $0.135 - $0.138
Card
********5094
Sản phẩm NhiMale
Card
********

SDT 0775469190
086215012345/ 0835089254

0775465401 dùng test luôn có otp

0775506894

0835089254


Step 3. Member declare OT trên TMS tại: https://tms.fsoft.com.vn/declare-my-ot-effort 
(Lưu ý declare đúng ngày, đúng type, đúng format Reason và Note)
LƯU Ý:
Khai theo đúng cột "Ngày OT" nghiệm thu trong file "Review_OT" hoặc "DATE" trên PTT
Detail Reason và Note bắt buộc đúng format: “Nghiệm thu OT tháng N. Ngày dd/mm”
📌 Expect:
Nghiệm thu ngày nào → khai đúng ngày đó
Nếu rơi vào TH đặc biệt cho phép khai sai ngày:
1. OT thứ 7 (OT thứ 7 hệ số tính như ngày thường nên log qua ngày trong tuần)
2. OT tháng N-1 (Do nghiệm thu có case từ 24/N-1 đến 31/N-1)
3. OT quá 4h ngày thường


Nghiệm thu OT tháng N. Ngày dd/mm


sau 22h mới tính đêm và không được quá 4 h ot 1 ngày
note nagfy 23/03 còn 2 tiếng


24-25 mới mở

/ mẫu test case


 tạo flow session trên RAI Portal ở phase Verify cho các tickets, rồi chuyển session Done với cac ticket này:, và chuyển status ticket sang complete: 


https://ptt-public.frt.vn/app/process-closing-shop#/dev
13:42
messsage-status
https://ptt.frt.vn/builder/auth/login
13:44
messsage-status
MT TEST PTT https://ptt-public.frt.vn/builder/apps
13:53
messsage-status
mt test prod PTT https://ptt-public.frt.vn/builder/auth/login
13:56
messsage-status
https://ptt.frt.vn/builder/apps
14:04
messsage-status
Khang Le Duy

https://ptt.frt.vn/builder/apps

https://bapp.frt.vn/builder/auth/login
